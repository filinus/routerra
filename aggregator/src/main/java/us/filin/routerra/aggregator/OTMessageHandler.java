package us.filin.routerra.aggregator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.util.StringUtils;
import us.filin.routerra.aggregator.message.OwntracksLocation;
import us.filin.routerra.aggregator.message.OwntracksMessage;
import us.filin.routerra.data.jpa.CMLogin;
import us.filin.routerra.data.jpa.Device;
import us.filin.routerra.data.jpa.Location;
import us.filin.routerra.data.service.DeviceRepository;
import us.filin.routerra.data.service.LocationRepository;
import us.filin.routerra.data.service.Repositories;

import java.io.IOException;

public class OTMessageHandler implements MessageHandler {
    static final private Logger LOG = LoggerFactory.getLogger(OTMessageHandler.class);

    @Autowired
    private Repositories repositories;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

        Message<?> rawMessage = message;
        Message<Object> messageString = ((Message<Object>)rawMessage);
        String payload = (String) messageString.getPayload();

        LOG.info("payload: \n\t{}\n\t{}\n\t{}\n\t{}", message, payload.getClass(), payload, message.getHeaders());

        DeviceRepository deviceRepository = repositories.getDevice();
        try {
            //TODO: extract and validate MQTT message
            MessageHeaders messageHeaders = message.getHeaders();
            String topic = (String) messageHeaders.get("mqtt_receivedTopic");
            if (StringUtils.isEmpty(topic)) {
                LOG.warn("header mqtt_receivedTopic not found");
                return;
            }
            String[] chunks = topic.split("/");
            String cmLoginName = chunks[1];
            String devName = chunks[2];

            CMLogin cmLogin = repositories.getCmLogin().findByLogin(cmLoginName);

            final String deviceId = getDeviceIdByLoginAndDevname(cmLogin, devName);

            OwntracksMessage owntracksMessage = objectMapper.readValue(payload, OwntracksMessage.class);
            LOG.info("parsed payload: \n\t{}", owntracksMessage);

            if ("location".equals(owntracksMessage.getType())) {
                saveLocation((OwntracksLocation) owntracksMessage, deviceId);

                deviceRepository.updateLocation(deviceId);
            }

        } catch (IOException e) {
            throw new MessagingException(e.getMessage(), e);
        }

    }

    /**
     * finds login's device in db, and creates if not found
     * Benefit of having this function separate: if we decided to never accept unknown devices, we could change logic here
     *
     * @param cmLogin
     * @param devName
     * @return always return device
     */
    public String getDeviceIdByLoginAndDevname(CMLogin cmLogin, String devName) {
        Device device = repositories.getDevice().findByLoginAndDevname(cmLogin, devName);
        if (device == null) {
            device = new Device(cmLogin.getFleet(), cmLogin, devName, null);
            LOG.debug("new device {}", device);
            device = repositories.getDevice().save(device);
        }
        LOG.debug("device {}", device);
        return device.getId();
    }

    /**
     * save device location into a log-like table
     *
     * @param owntracksLocation
     * @param deviceId
     * @return
     */
    public Location saveLocation(OwntracksLocation owntracksLocation, String deviceId) {
        LocationRepository locationRepository = repositories.getLocation();
        Location location = Location
                .builder()
                .course(owntracksLocation.getCourse())
                .latitude(owntracksLocation.getLatitude())
                .longitude(owntracksLocation.getLongitude())
                .device(deviceId)
                .tstamp(owntracksLocation.getDate())
                .build();

        Location saved = locationRepository.save(location);
        LOG.info("saved location {}", saved);
        return saved;
    }


}

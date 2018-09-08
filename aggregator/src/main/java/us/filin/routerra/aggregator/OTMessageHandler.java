package us.filin.routerra.aggregator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import us.filin.routerra.aggregator.message.OwntracksLocation;
import us.filin.routerra.aggregator.message.OwntracksMessage;
import us.filin.routerra.data.jpa.CMLogin;
import us.filin.routerra.data.jpa.Device;
import us.filin.routerra.data.jpa.Fleet;
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

        String payload = ((Message<String>)message).getPayload();

        LOG.info("payload: \n\t{}\n\t{}\n\t{}\n\t{}", message, payload.getClass(), payload, message.getHeaders());

        DeviceRepository deviceRepository = repositories.getDevice();
        LocationRepository locationRepository = repositories.getLocation();
        try {
            MessageHeaders messageHeaders = message.getHeaders();
            String topic = (String) messageHeaders.get("mqtt_receivedTopic");
            String[] chunks = topic.split("/");
            String cmLoginName = chunks[1];
            String devName = chunks[2];
            //Fleet fleet = repositories.lookupFleetByCMLogin(cmLoginName);
            CMLogin cmLogin = repositories.getCmLogin().findByLogin(cmLoginName);

            Device device = repositories.getDevice().findByLoginAndDevname(cmLogin, devName);
            if (device == null) {
                device = new Device(cmLogin.getFleet(), cmLogin, devName, null);
                device = repositories.getDevice().save(device);
            }
            final String deviceId = device.getId();
            LOG.info("device {} {}", deviceId, device);

            OwntracksMessage owntracksMessage = objectMapper.readValue(payload, OwntracksMessage.class);
            LOG.info("parsed payload: \n\t{}", owntracksMessage);

            if ("location".equals(owntracksMessage.getType())) {
                OwntracksLocation owntracksLocation = (OwntracksLocation) owntracksMessage;
                Location location = Location
                        .builder()
                        .course(owntracksLocation.getCourse())
                        .latitude(owntracksLocation.getLatitude())
                        .longitude(owntracksLocation.getLongitude())
                        //.device(null)
                        .build();

                Location saved = locationRepository.saveAndFlush(location);

                LOG.info("saved location {} {}", saved.getId(), saved);

                Location location2 = locationRepository.getOne(saved.getId());

                LOG.info("saved location check {} {}", location2);

                device.setLastLocation(location2);
                deviceRepository.save(device);

                Device device2 = deviceRepository.getOne(deviceId);
                LOG.info("device {} {}", device2.getId(), device2);

            }

        } catch (IOException e) {
            throw new MessagingException(e.getMessage(), e);
        }

    }
}

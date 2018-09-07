package us.filin.routerra.aggregator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import us.filin.routerra.aggregator.message.OwntracksLocation;
import us.filin.routerra.aggregator.message.OwntracksMessage;
import us.filin.routerra.data.jpa.Location;
import us.filin.routerra.data.service.Repositories;

import java.io.IOException;

public class OTMessageHandler implements MessageHandler {
    static final private Logger LOG = LoggerFactory.getLogger(OTMessageHandler.class);

    @Autowired
    private Repositories repositories;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

        Object payload = message.getPayload();

        LOG.info("payload: \n\t{}\n\t{}\n\t{}\n\t{}", message, payload.getClass(), payload, message.getHeaders());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


        try {
            OwntracksMessage owntracksMessage = objectMapper.readValue("" + payload, OwntracksMessage.class);
            LOG.info("parsed payload: \n\t{}", owntracksMessage);
            if ("location".equals(owntracksMessage.getType())) {
                OwntracksLocation owntracksLocation = (OwntracksLocation) owntracksMessage;
                Location location = Location
                        .builder()
                        .course(owntracksLocation.getCourse())
                        .lattitude(owntracksLocation.getLatitude())
                        .longitude(owntracksLocation.getLongitude())
                        .build();

                Location saved = repositories.getLocation().save(location);
                LOG.info("saved location {} {}", saved.getId(), saved);
            }

        } catch (IOException e) {
            throw new MessagingException(e.getMessage(), e);
        }

    }
}

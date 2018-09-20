package us.filin.routerra.aggregator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import us.filin.routerra.aggregator.message.OwntracksLocation;
import us.filin.routerra.data.jpa.CMLogin;
import us.filin.routerra.data.jpa.Fleet;
import us.filin.routerra.data.jpa.Location;
import us.filin.routerra.data.service.CMLoginRepository;
import us.filin.routerra.data.service.DeviceRepository;
import us.filin.routerra.data.service.LocationRepository;
import us.filin.routerra.data.service.Repositories;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OTMessageHandlerTest {
    @Mock
    Fleet fleet;

    @Mock
    CMLogin cmLogin;

    @Mock
    Location location;

    @Mock
    DeviceRepository deviceRepository;

    @Mock
    CMLoginRepository cmLoginRepository;

    @Mock
    LocationRepository locationRepository;

    @Mock
    Repositories repositories;

    @Spy
    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    OwntracksLocation owntracksLocation;

    @InjectMocks
    @Spy
    OTMessageHandler otMessageHandler = new OTMessageHandler();


    private Message<?> createMessage(final String topic, final String payload) {
        return new Message<Object>() {
            @Override
            public Object getPayload() {
                return payload;
            }

            @Override
            public MessageHeaders getHeaders() {
                return new MessageHeaders(Collections.singletonMap("mqtt_receivedTopic", topic));
            }
        };
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(repositories.getCmLogin()).thenReturn(cmLoginRepository);
        when(repositories.getDevice()).thenReturn(deviceRepository);
        when(repositories.getLocation()).thenReturn(locationRepository);
    }

    @Test
    public void testSaveLocation() {
        otMessageHandler.saveLocation(owntracksLocation, "someDeviceId");
    }


    @Test
    public void handleLocationMessage() {
        when(cmLoginRepository.findByLogin(anyString())).thenReturn(cmLogin);

        final String topic = "owntracks/user1/device1";

        Message<?> notLocationMessage = createMessage(topic, "{}");
        Message<?> locationMessage = createMessage(topic, "{\"_type\":\"location\",\"lat\":\"11.1\",\"lon\":\"22.2\"}");

        doReturn("deviceId").when(otMessageHandler).getDeviceIdByLoginAndDevname(any(), anyString());

        otMessageHandler.handleMessage(notLocationMessage);
        verify(otMessageHandler, never()).saveLocation(any(), anyString());

        otMessageHandler.handleMessage(locationMessage);
        verify(otMessageHandler, times(1)).saveLocation(any(), anyString());
    }
}
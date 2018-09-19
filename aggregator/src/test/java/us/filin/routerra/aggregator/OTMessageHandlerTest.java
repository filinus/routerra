package us.filin.routerra.aggregator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import us.filin.routerra.data.jpa.CMLogin;
import us.filin.routerra.data.jpa.Device;
import us.filin.routerra.data.jpa.Fleet;
import us.filin.routerra.data.jpa.Location;
import us.filin.routerra.data.service.CMLoginRepository;
import us.filin.routerra.data.service.DeviceRepository;
import us.filin.routerra.data.service.Repositories;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
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
    Repositories repositories;

    @InjectMocks
    @Resource
    OTMessageHandler otMessageHandler = new OTMessageHandler();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(repositories.getCmLogin()).thenReturn(cmLoginRepository);
        when(repositories.getDevice()).thenReturn(deviceRepository);
    }

    @Test @Ignore
    public void handleMessage() {
        Device device = Device
                .builder()
                .devname("devname")
                .fleet(fleet)
                .login(cmLogin)
                .lastLocation(location)
                .build();
        device.setId("id");
        when(cmLoginRepository.findByLogin(anyString())).thenReturn(cmLogin);
        when(deviceRepository.findByLoginAndDevname(any(CMLogin.class), anyString())).thenReturn(device);

        final Map<String, Object> map = new HashMap<String, Object>() {{
            put("mqtt_receivedTopic", "owntracks/user1/device1");
        }};


        Message<?> message = new Message<Object>() {
            @Override
            public Object getPayload() {
                return "{}";
            }

            @Override
            public MessageHeaders getHeaders() {
                return new MessageHeaders(map);
            }
        };

        otMessageHandler.handleMessage(message);
    }
}
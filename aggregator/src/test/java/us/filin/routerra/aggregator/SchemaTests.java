package us.filin.routerra.aggregator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class SchemaTests {

    @Test
    public void locationToJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OwntracksLocation owntracksLocation = OwntracksLocation
                .builder()
                .course(1.1)
                .latitude(2.2)
                .longitude(3.3)
                .timestamp(1535903074L)
                .build();

        String json = objectMapper.writeValueAsString(owntracksLocation);
        assertNotNull(json);

    }

    @Test
    public void jsonToLocation() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String jsonInString = "{\"_type\":\"location\",\"tid\":\"LG\",\"acc\":16,\"batt\":0,\"lat\":37.5042343,\"lon\":-122.2615189,\"tst\":1535903074}";

        OwntracksMessage owntracksMessage = objectMapper.readValue(jsonInString, OwntracksMessage.class);
        assertNotNull(owntracksMessage);
        assertEquals("location", owntracksMessage.getType());
        assertThat(owntracksMessage, instanceOf(OwntracksLocation.class));
        OwntracksLocation owntracksLocation = (OwntracksLocation) owntracksMessage;
        assertEquals(Double.valueOf(37.5042343), owntracksLocation.getLatitude());
        assertEquals(Double.valueOf(-122.2615189), owntracksLocation.getLongitude());
        assertNull(owntracksLocation.getCourse());
        assertEquals(Long.valueOf(1535903074L), owntracksLocation.getTimestamp());

    }

}

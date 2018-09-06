package us.filin.routerra.aggregator.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class SchemaTests {
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Test
    public void locationToJson() throws IOException {
        OwntracksLocation owntracksLocation = new OwntracksLocation();
        owntracksLocation.setType("location");
        owntracksLocation.setTimestamp(1535903074L);
        owntracksLocation.setCourse(1.1);
        owntracksLocation.setLatitude(2.2);
        owntracksLocation.setLongitude(3.3);

        String json = objectMapper.writeValueAsString(owntracksLocation);
        assertNotNull(json);
        assertEquals("{", json.substring(0,1));
        Map<String, String> set = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
        assertNotNull(set);
        assertEquals("1.1", set.get("cog"));
        assertEquals("2.2", set.get("lat"));
        assertEquals("3.3", set.get("lon"));
        assertEquals("1535903074", set.get("tst"));
        assertEquals("location", set.get("_type"));
    }

    @Test
    public void transitionToJson() throws IOException {
        OwntracksLocation owntracksLocation = new OwntracksLocation();
        owntracksLocation.setType("transition");
        owntracksLocation.setTimestamp(1535903074L);
        owntracksLocation.setCourse(0.0);
        owntracksLocation.setLatitude(0.0);
        owntracksLocation.setLongitude(0.0);

        String json = objectMapper.writeValueAsString(owntracksLocation);
        assertNotNull(json);
        assertEquals("{", json.substring(0,1));
        Map<String, String> set = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
        assertNotNull(set);
        assertEquals("0.0", set.get("cog"));
        assertEquals("0.0", set.get("lat"));
        assertEquals("0.0", set.get("lon"));
        assertEquals("1535903074", set.get("tst"));
        assertEquals("transition", set.get("_type"));
    }

    @Test
    public void jsonToLocation() throws IOException {
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

    @Test
    public void jsonToTransition() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String jsonInString = "{\"_type\":\"transition\",\"tid\":\"LG\",\"acc\":16,\"batt\":0,\"lat\":37.5042343,\"lon\":-122.2615189,\"tst\":1535903074}";

        OwntracksMessage owntracksMessage = objectMapper.readValue(jsonInString, OwntracksMessage.class);
        assertNotNull(owntracksMessage);
        assertEquals("transition", owntracksMessage.getType());
        assertThat(owntracksMessage, instanceOf(Located.class));
        OwntracksTransition owntracksLocation = (OwntracksTransition) owntracksMessage;
        assertEquals(Double.valueOf(37.5042343), owntracksLocation.getLatitude());
        assertEquals(Double.valueOf(-122.2615189), owntracksLocation.getLongitude());
        assertNull(owntracksLocation.getCourse());
        assertEquals(Long.valueOf(1535903074L), owntracksLocation.getTimestamp());

    }


    @Test
    public void jsonToLWT() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String jsonInString = "{\"tst\":\"1536168019\",\"_type\":\"lwt\"}";

        OwntracksMessage owntracksMessage = objectMapper.readValue(jsonInString, OwntracksMessage.class);
        assertNotNull(owntracksMessage);
        assertEquals("lwt", owntracksMessage.getType());
        assertThat(owntracksMessage, instanceOf(OwntracksLWT.class));
        OwntracksLWT owntracksLWT = (OwntracksLWT) owntracksMessage;
        assertEquals(Long.valueOf(1536168019L), owntracksLWT.getTimestamp());

    }

}

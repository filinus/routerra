package us.filin.routerra.aggregator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import us.filin.routerra.aggregator.message.OwntracksLocation;
import us.filin.routerra.aggregator.message.OwntracksMessage;
import us.filin.routerra.data.jpa.Location;
import us.filin.routerra.data.service.Repositories;

import java.io.IOException;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
@ComponentScan(basePackages = {"us.filin.routerra.data.*"})
@EnableJpaRepositories(basePackages = {"us.filin.routerra.data.*"})
@EntityScan("us.filin.routerra.data.*")
public class AggregatorApplication {

	static final private Logger LOG = LoggerFactory.getLogger(AggregatorApplication.class);
	static final private String TOPIC_OWNTRACKS = "owntracks/app-user/+";

	@Value("${mqtt.default.client.id}")
	private String cloudMqttCliendId;

	@Value(TOPIC_OWNTRACKS)
	private String topicOwntracks;

	@Autowired
	private MqttPahoClientFactory clientFactory;

	@Autowired
	private Repositories repositories;

	public static void main(String[] args) {
		new SpringApplicationBuilder(AggregatorApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		return new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				Object payload = message.getPayload();

				LOG.info("payload: \n\t{}\n\t{}\n\t{}\n\t{}", message, payload.getClass(), payload, message.getHeaders());

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


				try {
					OwntracksMessage owntracksMessage = objectMapper.readValue(""+payload, OwntracksMessage.class);
					LOG.info("parsed payload: \n\t{}", owntracksMessage);
					if ("location".equals(owntracksMessage.getType())) {
						OwntracksLocation owntracksLocation = (OwntracksLocation) owntracksMessage;
						Location location = Location
								.builder()
								.cource(owntracksLocation.getCourse())
								.lattitude(owntracksLocation.getLatitude())
								.logitude(owntracksLocation.getLongitude())
								.build();

						Location saved = repositories.getLocationRepository().save(location);
						LOG.info("saved location: {}", saved);
					}

				} catch (IOException e) {
					throw new MessagingException(e.getMessage(), e);
				}
			}

		};
	}
}

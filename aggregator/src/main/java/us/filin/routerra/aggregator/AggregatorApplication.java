package us.filin.routerra.aggregator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class AggregatorApplication {

	static final private Logger LOG = LoggerFactory.getLogger(AggregatorApplication.class);
	static final private String TOPIC_OWNTRACKS = "owntracks/app-user/+";

	@Value("${mqtt.default.client.id}")
	private String cloudMqttCliendId;

	@Value(TOPIC_OWNTRACKS)
	private String topicOwntracks;

	@Autowired
	private MqttPahoClientFactory clientFactory;

	public static void main(String[] args) {
		new SpringApplicationBuilder(AggregatorApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		return message -> LOG.info("payload: \n\t{}\n\t{}\n\t{}", message, message.getPayload(), message.getHeaders());
	}
}

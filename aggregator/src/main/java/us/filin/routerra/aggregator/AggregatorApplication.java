package us.filin.routerra.aggregator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import us.filin.routerra.data.RouterraDatabase;


@SpringBootApplication
@ImportResource("classpath:beans.xml")
@RouterraDatabase
@ComponentScan(basePackages = {"us.filin.routerra.data.service"})
@Slf4j
public class AggregatorApplication {

	static final private String TOPIC_OWNTRACKS = "owntracks/+/+"; //"owntracks/app-user/+";

	@Value("${mqtt.default.client.id}")
	private String cloudMqttCliendId;

	@Value(TOPIC_OWNTRACKS)
	private String topicOwntracks;

	//@Autowired
	//private MqttPahoClientFactory clientFactory;

	public static void main(String[] args) {
		log.info("Starting Aggregator with args {}", args);
		new SpringApplicationBuilder(AggregatorApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		log.info("creating message handler ...");
		return new OTMessageHandler();
	}
}

package us.filin.routerra.aggregator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AggregatorApplication {

	static final private Logger LOG = LoggerFactory.getLogger(AggregatorApplication.class);
	static final private String TOPIC_OWNTRACKS = "owntracks/+/+"; //"owntracks/app-user/+";

	@Value("${mqtt.default.client.id}")
	private String cloudMqttCliendId;

	@Value(TOPIC_OWNTRACKS)
	private String topicOwntracks;

	//@Autowired
	//private MqttPahoClientFactory clientFactory;

	public static void main(String[] args) {
		new SpringApplicationBuilder(AggregatorApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		return new OTMessageHandler();
	}
}

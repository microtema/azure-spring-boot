package de.microtema.azure.servicebus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "service-bus")
public record ServiceBusProperties(
        String connectionString,
        String queueName,
        String topicName,
        String topicSubscription) {
}

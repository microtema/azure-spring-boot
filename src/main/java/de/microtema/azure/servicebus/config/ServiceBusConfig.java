package de.microtema.azure.servicebus.config;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import de.microtema.azure.servicebus.consumer.ProcessDefinitionQueueConsumer;
import de.microtema.azure.servicebus.consumer.ProcessDefinitionTopicConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBusConfig {

    @Bean
    ServiceBusSenderClient serviceBusSenderQueueClient(ServiceBusProperties properties) {

        return new ServiceBusClientBuilder()
                .connectionString(properties.connectionString())
                .sender()
                .queueName(properties.queueName())
                .buildClient();
    }

    @Bean
    ServiceBusProcessorClient serviceBusProcessorQueueClient(ServiceBusProperties properties, ProcessDefinitionQueueConsumer processDefinitionQueueConsumer) {

        var serviceBusProcessorClient = new ServiceBusClientBuilder()
                .connectionString(properties.connectionString())
                .processor()
                .queueName(properties.queueName())
                .processMessage(processDefinitionQueueConsumer::processMessage)
                .processError(processDefinitionQueueConsumer::processError)
                .buildProcessorClient();

        serviceBusProcessorClient.start();

        return serviceBusProcessorClient;
    }

    @Bean
    ServiceBusSenderClient serviceBusSenderTopicClient(ServiceBusProperties properties) {

        return new ServiceBusClientBuilder()
                .connectionString(properties.connectionString())
                .sender()
                .topicName(properties.topicName())
                .buildClient();
    }

    @Bean
    ServiceBusProcessorClient serviceBusSessionReceiverClient(ServiceBusProperties properties, ProcessDefinitionTopicConsumer processDefinitionConsumer) {

        var serviceBusProcessorClient = new ServiceBusClientBuilder()
                .connectionString(properties.connectionString())
                .processor()
                .topicName(properties.topicName())
                .subscriptionName(properties.topicSubscription())
                .processMessage(processDefinitionConsumer::processMessage)
                .processError(processDefinitionConsumer::processError)
                .buildProcessorClient();

        serviceBusProcessorClient.start();

        return serviceBusProcessorClient;
    }
}

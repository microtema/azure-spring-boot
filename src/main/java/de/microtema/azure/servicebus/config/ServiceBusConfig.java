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
    ServiceBusSenderClient serviceBusSenderQueueClient() {

        return new ServiceBusClientBuilder()
                .connectionString("Endpoint=sb://microtema.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=xL61ZJbUD3Ys2Q9Fc+a/VbN392Sr/zV+rmn8/c5/6b0=")
                .sender()
                .queueName("microtema.process.definition.queue")
                .buildClient();
    }

    @Bean
    ServiceBusProcessorClient serviceBusProcessorQueueClient(ProcessDefinitionQueueConsumer processDefinitionQueueConsumer) {

        var serviceBusProcessorClient = new ServiceBusClientBuilder()
                .connectionString("Endpoint=sb://microtema.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=xL61ZJbUD3Ys2Q9Fc+a/VbN392Sr/zV+rmn8/c5/6b0=")
                .processor()
                .queueName("microtema.process.definition.queue")
                .processMessage(processDefinitionQueueConsumer::processMessage)
                .processError(processDefinitionQueueConsumer::processError)
                .buildProcessorClient();

        serviceBusProcessorClient.start();

        return serviceBusProcessorClient;
    }

    @Bean
    ServiceBusSenderClient serviceBusSenderTopicClient() {

        return new ServiceBusClientBuilder()
                .connectionString("Endpoint=sb://microtema.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=xL61ZJbUD3Ys2Q9Fc+a/VbN392Sr/zV+rmn8/c5/6b0=")
                .sender()
                .topicName("microtema.process.definition.topic")
                .buildClient();
    }

    @Bean
    ServiceBusProcessorClient serviceBusSessionReceiverClient(ProcessDefinitionTopicConsumer processDefinitionConsumer) {

        var serviceBusProcessorClient = new ServiceBusClientBuilder()
                .connectionString("Endpoint=sb://microtema.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=xL61ZJbUD3Ys2Q9Fc+a/VbN392Sr/zV+rmn8/c5/6b0=")
                .processor()
                .topicName("microtema.process.definition.topic")
                .subscriptionName("microtema.subscription")
                .processMessage(processDefinitionConsumer::processMessage)
                .processError(processDefinitionConsumer::processError)
                .buildProcessorClient();

        serviceBusProcessorClient.start();

        return serviceBusProcessorClient;
    }
}

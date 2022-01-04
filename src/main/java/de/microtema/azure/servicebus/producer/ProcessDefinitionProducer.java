package de.microtema.azure.servicebus.producer;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.microtema.azure.commons.model.ProcessDefinition;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessDefinitionProducer {

    private final ObjectMapper objectMapper;

    private final ServiceBusSenderClient serviceBusSenderQueueClient;

    private final ServiceBusSenderClient serviceBusSenderTopicClient;


    @SneakyThrows
    @PostConstruct
    private void sendQueueMessage() {

        var processDefinition = new ProcessDefinition(new Random().nextInt(1000), "Invoice Process");

        var json = objectMapper.writeValueAsString(processDefinition);

        var message = new ServiceBusMessage(json);

        serviceBusSenderQueueClient.sendMessage(message);
    }

    @SneakyThrows
    @PostConstruct
    private void sendTopicMessage() {

        var processDefinition = new ProcessDefinition(new Random().nextInt(1000), "Invoice Process");

        var json = objectMapper.writeValueAsString(processDefinition);

        var message = new ServiceBusMessage(json);
        message.setSessionId(UUID.randomUUID().toString());

        serviceBusSenderTopicClient.sendMessage(message);
    }
}

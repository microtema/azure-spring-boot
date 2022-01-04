package de.microtema.azure.servicebus.consumer;

import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.microtema.azure.commons.model.ProcessDefinition;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProcessDefinitionTopicConsumer {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void processMessage(ServiceBusReceivedMessageContext messageContext) {

        var message = messageContext.getMessage();

        var binaryData = message.getBody();

        var json = new String(binaryData.toBytes());

        var processDefinition = objectMapper.readValue(json, ProcessDefinition.class);

        log.info("message from topic {}", processDefinition);
    }

    public void processError(ServiceBusErrorContext errorContext) {

        var errorSource = errorContext.getErrorSource();
        var entityPath = errorContext.getEntityPath();
        var fullyQualifiedNamespace = errorContext.getFullyQualifiedNamespace();
        var exception = errorContext.getException();

        log.error("process error from topic {} {} {} {}", errorSource, entityPath, fullyQualifiedNamespace, exception);
    }
}

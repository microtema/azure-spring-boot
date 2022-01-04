package de.microtema.azure.blobstorage.service;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.microtema.azure.commons.model.ProcessDefinition;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

@Log4j2
@Service
@RequiredArgsConstructor
public class BlobStorageWriteService {

    private final ObjectMapper objectMapper;

    private final BlobServiceClient blobServiceClient;

    @SneakyThrows
    @PostConstruct
    private void init() {

        var blobContainerItems = blobServiceClient.listBlobContainers();

        blobContainerItems.forEach(it -> log.info("Listing blob container name {} version {}", it.getName(), it.getVersion()));

        var blobContainer = blobServiceClient.getBlobContainerClient("microtema");

        if (!blobContainer.exists()) {

            blobContainer = blobServiceClient.createBlobContainer("microtema");
        }

        var blobClient = blobContainer.getBlobClient("api-payload.json");

        var processDefinition = new ProcessDefinition(new Random().nextInt(1000), "Invoice Process");
        var payload = objectMapper.writeValueAsString(processDefinition);

        var binaryData = BinaryData.fromBytes(payload.getBytes());

        blobClient.upload(binaryData, true);

        log.info("blob storage upload {}", processDefinition);
    }
}

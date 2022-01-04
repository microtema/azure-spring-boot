package de.microtema.azure.blobstorage.service;

import com.azure.storage.blob.BlobServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.microtema.azure.commons.model.ProcessDefinition;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Log4j2
@Service
@RequiredArgsConstructor
public class BlobStorageReadService {

    private final ObjectMapper objectMapper;

    private final BlobServiceClient blobServiceClient;

    @SneakyThrows
    @PostConstruct
    private void init() {

        var blobContainer = blobServiceClient.getBlobContainerClient("microtema");

        if (!blobContainer.exists()) {

            return;
        }

        var blobClient = blobContainer.getBlobClient("api-payload.json");

        var binaryData = blobClient.downloadContent();

        var processDefinition = objectMapper.readValue(binaryData.toString(), ProcessDefinition.class);

        log.info("get blob storage {}", processDefinition);
    }
}

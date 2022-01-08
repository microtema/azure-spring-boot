package de.microtema.azure.blobstorage.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlobStorageConfig {

    @Bean
    BlobServiceClient blobServiceClient(BlobStorageProperties properties) {

        return new BlobServiceClientBuilder()
                .connectionString(properties.connectionString())
                .buildClient();
    }
}

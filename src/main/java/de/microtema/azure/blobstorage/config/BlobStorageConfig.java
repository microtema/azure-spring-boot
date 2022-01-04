package de.microtema.azure.blobstorage.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlobStorageConfig {

    @Bean
    BlobServiceClient blobServiceClient() {

        return new BlobServiceClientBuilder()
                .connectionString("DefaultEndpointsProtocol=https;AccountName=microtema;AccountKey=SnbKHn2u3icuAoqPiR5Bw0mnDyYxV5GX/DKLY6AVRyMcrhcdc0MFuHBPjwUvUVRZpJVgOiYWzMX+Qarubeq4fw==;EndpointSuffix=core.windows.net")
                .buildClient();
    }
}

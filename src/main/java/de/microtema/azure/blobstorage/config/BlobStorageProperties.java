package de.microtema.azure.blobstorage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "blob-storage")
public record BlobStorageProperties(String connectionString) {
}

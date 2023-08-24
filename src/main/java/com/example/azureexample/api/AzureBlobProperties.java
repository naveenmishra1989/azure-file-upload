package com.example.azureexample.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("azure.myblob")
public class AzureBlobProperties {
    private String connectionstring;
    private String container;
}

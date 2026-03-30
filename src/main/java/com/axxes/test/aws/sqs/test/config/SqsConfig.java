package com.axxes.test.aws.sqs.test.config;

import io.awspring.cloud.autoconfigure.core.RegionProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
@ConfigurationProperties(prefix = "sqs")
@Getter
@Setter
public class SqsConfig {

    private String endpoint;
    private String queueName;

    @Bean
    public SqsAsyncClient sqsAsyncClient(AwsCredentialsProvider credentialsProvider, RegionProperties regionProperties) {
        return SqsAsyncClient.builder()
                .credentialsProvider(credentialsProvider)
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(regionProperties.getStatic()))
                .build();
    }

}

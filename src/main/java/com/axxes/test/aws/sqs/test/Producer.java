package com.axxes.test.aws.sqs.test;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.axxes.test.aws.sqs.test.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class Producer {

    private static final ObjectMapper objectMapper =  new ObjectMapper();

    @Value("${aws.queueName}")
    private String queueName;

    private final AmazonSQS amazonSQSClient;

    public Producer(AmazonSQS amazonSQSClient) {
        this.amazonSQSClient = amazonSQSClient;
    }

    public void publishMessage(String id) {
        try {
            GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(queueName);
            var message = Message.builder()
                    .id(id)
                    .content("message " + id)
                    .createdAt(new Date()).build();
            var jsonMessage = objectMapper.writeValueAsString(message);
            var result = amazonSQSClient.sendMessage(queueUrl.getQueueUrl(), jsonMessage);
            log.info("Message {} published: {}", jsonMessage, result);
        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
        }

    }

}

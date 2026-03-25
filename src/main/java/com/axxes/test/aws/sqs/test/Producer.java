package com.axxes.test.aws.sqs.test;

import com.axxes.test.aws.sqs.test.config.SqsConfig;
import com.axxes.test.aws.sqs.test.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {

    private static final String MESSAGE_GROUP_ID = "test-messages";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final SqsConfig sqsConfig;
    private final SqsTemplate sqsTemplate;

    public void publishMessage(String id) {
        try {
            var message = Message.builder()
                    .id(id)
                    .content("message " + id)
                    .createdAt(new Date()).build();
            var jsonMessage = objectMapper.writeValueAsString(message);
            var result = sqsTemplate.send(opts ->
                    opts.queue(sqsConfig.getQueueName())
                        .payload(jsonMessage)
                        .messageGroupId(MESSAGE_GROUP_ID)
                        .messageDeduplicationId(id)
            );
            log.info(">>> Published message {} with id {}", jsonMessage, result.messageId());
        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
        }

    }

}

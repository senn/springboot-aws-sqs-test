package com.axxes.test.aws.sqs.test;

import com.axxes.test.aws.sqs.test.model.Message;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class Consumer {

    @SqsListener("${sqs.queueName}")
    public void consumeMessages(Message message) {
        log.info("<<< Read Message from queue: {}", message);
    }

}

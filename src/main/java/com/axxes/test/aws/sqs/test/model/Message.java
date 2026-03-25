package com.axxes.test.aws.sqs.test.model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    private String id;
    private String content;
    private Date createdAt;

}

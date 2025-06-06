package com.voltpay.perf.test.producer;

import com.voltpay.perf.test.pojo.WriteEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.UUID;

@Service
@AllArgsConstructor
public class Producer {

    @Autowired
    private final KafkaTemplate<String, WriteEvent> kafkaTemplate;

    public void produceEvents(Integer count) {
        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < count; i++) {
            Long custId = rand.nextLong(99);
            WriteEvent writeEvent = WriteEvent.builder()
                    .messageId(UUID.randomUUID().toString())
                    .type("BWI")
                    .amount(new BigDecimal("123131.21"))
                    .currency("EUR")
                    .status(2)
                    .custId(custId)
                    .comment("Creating BWI trn in EUR")
                    .build();

            kafkaTemplate.send("write-topic", custId.toString(), writeEvent);
        }
    }

    public void produceWarmupEvents(Integer count) {
        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < count; i++) {
            Long custId = rand.nextLong(99);
            kafkaTemplate.send("write-topic", custId.toString(), new WriteEvent());
        }
    }
}

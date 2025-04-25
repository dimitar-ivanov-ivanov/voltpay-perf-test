package com.voltpay.perf.test.controller;

import com.voltpay.perf.test.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/write")
public class WriteController {

    @Autowired
    public Producer producer;

    @PostMapping("/{count}")
    public ResponseEntity<String> writeEvents(@PathVariable("count") Integer count) {
        //System.out.println("Staring production of " + count + " events at " + System.currentTimeMillis());
        producer.produceEvents(count);
        return ResponseEntity.ok("Produce successfully " + count + " events.");
    }

    @PostMapping("/warmup/{count}")
    public ResponseEntity<String> produceWarmUpEvents(@PathVariable("count") Integer count) {
        //System.out.println("Staring production of " + count + " warmup events at " + System.currentTimeMillis());
        producer.produceWarmupEvents(count);
        return ResponseEntity.ok("Produced successfully " + count + " warmup events");
    }
}

package com.cc.sentimentanalysis.service;

import com.cc.sentimentanalysis.models.LayoffMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;

@Component
public class CsvDataConsumer {

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;
    @Autowired
    private KafkaConsumer<String, LayoffMessage> consumer;


    @KafkaListener(topics = "layoffs-data", groupId = "layoffs-consumer-group")
    public void consumeData(String message) throws JsonProcessingException {
        System.out.println("Received message: " + message);
        ObjectMapper mapper = new ObjectMapper();
        LayoffMessage messageObject = mapper.readValue(message, LayoffMessage.class);

        // Perform sentiment analysis on tweetText
        String sentiment = sentimentAnalysisService.analyzeSentiment(messageObject.getMessage());

        // Process the sentiment (e.g., store in a database, log, etc.)
        System.out.println("Sentiment: " + sentiment);
    }

    /*public void consumeLayoffMessages() {
        consumer.subscribe(Collections.singleton("layoffs-data"));
        while (true) {
            ConsumerRecords<String, LayoffMessage> records = consumer.poll(Duration.ofMillis(2000));
            for (ConsumerRecord<String, LayoffMessage> record : records) {
                LayoffMessage layoffMessage = record.value();
                System.out.println(layoffMessage);
                String sentiment = sentimentAnalysisService.analyzeSentiment(layoffMessage.getMessage());
                System.out.println("Sentiment: " + sentiment);
            }
        }
    }*/

}

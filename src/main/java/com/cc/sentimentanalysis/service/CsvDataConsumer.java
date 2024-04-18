package com.cc.sentimentanalysis.service;

import com.cc.sentimentanalysis.models.LayoffMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

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

        // Perform sentiment analysis
        String sentiment = sentimentAnalysisService.analyzeSentiment(messageObject.getMessage());

        // Process the sentiment
        System.out.println("Sentiment: " + sentiment);
    }

}

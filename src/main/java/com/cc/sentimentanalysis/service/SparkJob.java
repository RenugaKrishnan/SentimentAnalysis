package com.cc.sentimentanalysis.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;


import java.util.*;


/*public class SparkJob {
    private SentimentAnalysisService sentimentAnalysisService;
    private final JavaSparkContext sparkContext;
    private final JavaStreamingContext streamingContext;

    public SparkJob(JavaSparkContext sparkContext, JavaStreamingContext streamingContext) {
        this.sparkContext = sparkContext;
        this.streamingContext = streamingContext;
    }

    public void run() throws InterruptedException {
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092");
        kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("group.id", "spark-streaming-consumer-group");

        Collection<String> topics = List.of("twitter-topic");

        JavaInputDStream<ConsumerRecord<String, String>> messages =
                KafkaUtils.createDirectStream(
                        streamingContext,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
                );

        JavaDStream<String> lines = messages.map(ConsumerRecord::value);

        // Perform sentiment analysis or further processing on the tweets
        lines.foreachRDD(rdd -> {
            rdd.foreachPartition(partitionOfRecords -> {
                partitionOfRecords.forEachRemaining(record -> {
                    String sentiment = sentimentAnalysisService.analyzeSentiment(record);
                    System.out.println("Tweet: " + record + ", Sentiment: " + sentiment);
                });
            });
        });

        streamingContext.start();
        streamingContext.awaitTermination();
    }
}*/


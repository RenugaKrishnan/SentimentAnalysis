package com.cc.sentimentanalysis.service;

import com.cc.sentimentanalysis.models.LayoffMessage;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.exceptions.CsvException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CsvDataProducer {

    private static final Logger logger = LoggerFactory.getLogger(CsvDataProducer.class);

    @Value("${csv.file.path}")
    private String csvFilePath;

    @Autowired
    private KafkaTemplate<String, LayoffMessage> kafkaProducer;

    @PostConstruct
    public void publishCsvData() throws FileNotFoundException {
        Path csvPath = Paths.get(csvFilePath);
        System.out.println(csvPath);

        Reader reader = new BufferedReader(new FileReader(csvFilePath));

        Map<String, String> columnMappings = Map.of(
                "text", "message",
                "role", "jobTitle",
                "num_reactions", "likes",
                "num_comments", "comments",
                "num_reposts", "reposts"
        );

        HeaderColumnNameTranslateMappingStrategy mappingStrategy =
                new HeaderColumnNameTranslateMappingStrategy();
        mappingStrategy.setColumnMapping(columnMappings);
        mappingStrategy.setType(LayoffMessage.class);

        CsvToBean<LayoffMessage> csvReader = new CsvToBeanBuilder(reader)
                .withType(LayoffMessage.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        List<LayoffMessage> results = csvReader.parse();

            // Send all parsed messages in bulk
            for (LayoffMessage message : results) {
                logger.info("Published Data: {}", message);
                kafkaProducer.send(new ProducerRecord<>("layoffs-data", message));
            }
    }
}
package com.cc.sentimentanalysis.service;

import com.cc.sentimentanalysis.models.SentimentType;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SentimentAnalysisService {

    private final StanfordCoreNLP pipeline;
    private static final Set<String> negationWords = Set.of("not", "don't", "no", "never");

    public SentimentAnalysisService() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
    }

    // Using Stanford CoreNLP
    public String analyzeSentiment(String text) {

        if (text == null || text.isEmpty()) {
            return "Neutral";
        }

        double totalSentiment = 0.0;
        int sentenceCount = 0;
        Annotation annotation = pipeline.process(text);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);

            // Handle negation
            boolean negation = false;
            for (CoreLabel token : tokens) {
                String word = token.originalText().toLowerCase();
                if (negationWords.contains(word)) {
                    negation = !negation;
                }
            }

            // Extract sentiment with negation handling
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentimentScore = RNNCoreAnnotations.getPredictedClass(tree);
            double adjustedScore = sentimentScore * (negation ? -1 : 1);

            totalSentiment += adjustedScore;
            sentenceCount++;
        }

        double averageSentiment = totalSentiment / sentenceCount;
        return SentimentType.fromValue((int) Math.round(averageSentiment)).toString();
    }
}


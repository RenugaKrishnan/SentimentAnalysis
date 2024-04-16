package com.cc.sentimentanalysis.config;

import com.cc.sentimentanalysis.constants.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterConfig {

    @Bean
    public TwitterStream twitterStream() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(Constants.CONSUMER_KEY)
                .setOAuthConsumerSecret(Constants.CONSUMER_SECRET)
                .setOAuthAccessToken(Constants.ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(Constants.ACCESS_TOKEN_SECRET);

        TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());
        return tf.getInstance();
    }
}



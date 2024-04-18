package com.cc.sentimentanalysis.config;

/*@Configuration
public class SentimentAnalysisModelConfig {

    @Bean
    public void downloadSpaCyModel() throws IOException, InterruptedException {
        CommandLine command = new CommandLine("python");
        command.addArgument("-m");
        command.addArgument("spacy");
        command.addArgument("download");
        command.addArgument("en_core_web_sm");
        DefaultExecuteResult result = new DefaultExecuteResultHandler().execute(command);

        if (result.getExitValue() != 0) {
            throw new RuntimeException("Failed to download spaCy model");
        }
    }
}*/

package com.cc.sentimentanalysis;

/*
@Component
public class LayoffDataSimulator implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(LayoffDataSimulator.class);

    @Value("${csv.file.path}")
    private String csvFilePath;

    @Autowired
    private KafkaTemplate<String, LayoffMessage> kafkaTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Path csvPath = Paths.get(csvFilePath);

        try (BufferedReader br = Files.newBufferedReader(csvPath)) {
            String line;
            while ((line = br.readLine()) != null) {
                LayoffMessage message = parseLine(line);
                if (message != null) {
                    logger.info("Published Data: {}", message);
                    kafkaTemplate.send(new ProducerRecord<>("layoffs-data", message));
                } else {
                    logger.warn("Skipping invalid line: {}", line);
                }
            }
        } catch (IOException e) {
            logger.error("Error reading CSV file: {}", e.getMessage());
        }
    }

    private LayoffMessage parseLine(String line) {
        String[] fields = line.split(",");
        if (fields.length >= 6) {
            try {
                LayoffMessage message = new LayoffMessage();
                message.setMessage(fields[0]);
                message.setJobTitle(fields[1]);
                message.setLikes(fields[2]);
                message.setComments(fields[3]);
                message.setReposts(fields[4]);
                return message;
            } catch (NumberFormatException e) {
                logger.warn("Error parsing line: {} - Invalid number format", line, e);
                return null;
            }
        }
        return null;
    }
}
*/



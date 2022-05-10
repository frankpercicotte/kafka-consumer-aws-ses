package br.com.kafka_consumer.config;


import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

public class ConfigAws {
    
    static AwsCredentialsProvider credentialsProvider = new AwsCredentialsProvider() {
        @Override
        public AwsCredentials resolveCredentials() {
            return new AwsCredentials() {
                @Override
                public String accessKeyId() {
                    return System.getenv("AWS_ACCESS_KEY");
                }
    
                @Override
                public String secretAccessKey() {
                    return System.getenv("AWS_SECRET_KEY");
                }
            };
        }
    };

    public static SesClient sesClient(){
        return SesClient.builder()
        .region(Region.US_EAST_1)
        .credentialsProvider(credentialsProvider)
        .build();     
    }   
}




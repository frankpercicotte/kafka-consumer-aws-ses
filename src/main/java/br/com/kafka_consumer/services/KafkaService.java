package br.com.kafka_consumer.services;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaService {
    public static String readMessage(String groupId) throws InterruptedException, ExecutionException {
        var consumer = new KafkaConsumer<String, String>(properties(groupId));
        consumer.subscribe(Collections.singletonList(System.getenv("KAFKA_TOPIC")));

        var msgs = consumer.poll(Duration.ofMillis(500));        
        for (ConsumerRecord<String, String> registro : msgs) {
            System.out.println("\nMensagem recebida: " + registro.value());
            consumer.close();
            return registro.value().toString();           
        }
        consumer.close();
        return null;        
    }

    private static Properties properties(String groupId) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, System.getenv("KAFKA_HOST"));
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        return properties;
    }
}

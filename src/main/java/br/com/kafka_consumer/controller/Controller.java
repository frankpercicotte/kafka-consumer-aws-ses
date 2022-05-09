package br.com.kafka_consumer.controller;

import java.util.concurrent.ExecutionException;

import br.com.kafka_consumer.services.KafkaService;

public class Controller {
    public static void handleController() throws InterruptedException {
        System.out.println("Lendo mensagens ...");
        var groupId = System.getenv("KAFKA_GROUP_ID_READER");
        

        System.out.println(groupId);
        System.out.println(System.getenv("KAFKA_TOPIC"));
        System.out.println(System.getenv("KAFKA_HOST"));

     
        while (true) {           
            try {
               KafkaService.readMessage(groupId);
            } catch (InterruptedException e) {
                System.out.println("Error listen kafka: ");
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println("Error listen kafka: ");
                e.printStackTrace();
            }           
        }
        
    }   
}

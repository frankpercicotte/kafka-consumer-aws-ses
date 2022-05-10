package br.com.kafka_consumer.controller;

import java.util.concurrent.ExecutionException;

import br.com.kafka_consumer.services.AwsSeS;
import br.com.kafka_consumer.services.KafkaService;

public class Controller {
    public static void handleController() throws InterruptedException {
        System.out.println("Lendo mensagens ...");
        var groupId = System.getenv("KAFKA_GROUP_ID_READER");
        
        System.out.println(groupId);
        System.out.println(System.getenv("KAFKA_TOPIC"));
        System.out.println(System.getenv("KAFKA_HOST"));
        String registro = null;
      

        while (true) {           
            // Listen kafkaSerice - consumer
            try {
               registro = KafkaService.readMessage(groupId);
            } catch (InterruptedException e) {
                System.out.println("Error listen kafka: ");
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println("Error listen kafka: ");
                e.printStackTrace();
            }

            // if something received by kafka 
            if(registro != null){
                // if msg = exit to finish app.
                if(registro.toLowerCase().equals("exit")){
                    break;
                }
                if (AwsSeS.sendMessage(registro)) {
                    System.out.println("Mensagem enviada com sucesso!");
                } else {
                    System.out.println("Ops problema ao enviar email!");
                }
            }
            
            // wait 2.5s to next check...
            Thread.sleep(2500);
            System.out.print(".");
        }        
    }   
}

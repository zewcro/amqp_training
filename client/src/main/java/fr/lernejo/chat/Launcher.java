package fr.lernejo.chat;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Launcher.class);
        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        template.setRoutingKey("chat_messages");

        while(true) {
            System.out.println("Input a message, we will sent it for you (q for quit)");

            String s = scanner.nextLine();
            if(s.equals("q")) {
                System.out.println("Bye.");
                System.exit(0);
            }

            template.convertAndSend(s);
            System.out.println("Message sent.");
        }
    }
}
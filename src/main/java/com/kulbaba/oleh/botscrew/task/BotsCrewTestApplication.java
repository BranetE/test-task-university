package com.kulbaba.oleh.botscrew.task;

import com.kulbaba.oleh.botscrew.task.command.CommandInvoker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

@SpringBootApplication
@RequiredArgsConstructor
public class BotsCrewTestApplication implements CommandLineRunner {

    private final CommandInvoker commandInvoker;

    public static void main(String[] args) {
        SpringApplication.run(BotsCrewTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(in);
        String greetingScreen = """
                  _   _       _                    _ _        \s
                 | | | |_ __ (_)_   _____ _ __ ___(_) |_ _   _\s
                 | | | | '_ \\| \\ \\ / / _ \\ '__/ __| | __| | | |
                 | |_| | | | | |\\ V /  __/ |  \\__ \\ | |_| |_| |
                  \\___/|_| |_|_| \\_/ \\___|_|  |___/_|\\__|\\__, |
                                                         |___/\s
                """;
        out.println(greetingScreen);
        while (true) {
            out.println("Enter command:");
            String userInput = sc.nextLine();
            if(userInput.equalsIgnoreCase("exit")){
                break;
            }
            try {
                commandInvoker.handleUserInput(userInput);
                out.println("---------------------------------------");
            }catch (RuntimeException e){
                out.println(e.getMessage());
                out.println("---------------------------------------");
            }
        }
    }
}

package com.example.botscrew.task;

import com.example.botscrew.task.command.CliCommandManager;
import jakarta.persistence.EntityNotFoundException;
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

    private final CliCommandManager cliCommandManager;

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
                cliCommandManager.handleUserInput(userInput);
                out.println("---------------------------------------");
            }catch (EntityNotFoundException e){
                out.println(e.getMessage());
                out.println("---------------------------------------");
            }
        }
    }
}

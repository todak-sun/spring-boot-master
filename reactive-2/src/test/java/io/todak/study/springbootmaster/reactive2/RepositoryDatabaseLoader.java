package io.todak.study.springbootmaster.reactive2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RepositoryDatabaseLoader {

    @Bean
    CommandLineRunner initialize(BlockingItemRepository repository) {
        return args -> {
            repository.save(new Item("Alf alarm clock", "kids clock", 19.99));
            repository.save(new Item("Smurf TV tray", "kids TV tray", 24.99));
        };
    }
}
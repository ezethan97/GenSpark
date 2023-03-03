package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BeanConfig {
        @Bean
        public Address getAddress(){
            return new Address("Atlanta", "Georgia", "USA", "12345");
        }

        @Bean
        public Phone getCell(){
            return new Phone("336-123-4567");
        }

        @Bean
        public Phone getHome(){
            return new Phone("336-321-8989");
        }

        @Bean
        @Autowired
        public student getStudent(List<Phone> phones, Address address){
            return new student(1, "Ethan", phones, address);
        }

}

package com.example.mvccrud;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MvcCrudApplication {
   // @Bean
    public ApplicationRunner runner(){
        int[] arr={1,2,3,4,5,6};//[1,2,3,5,6]

        return r->{
            List<String> list=List.of("Apple","Orange","Banana");
            //must be implements iterable
            for(int i=0;i<list.size();i++){

            }
            for(var fruit:list){
                System.out.println(fruit);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MvcCrudApplication.class, args);
    }

}

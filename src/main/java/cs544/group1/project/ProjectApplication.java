package cs544.group1.project;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProjectApplication.class, args);
        
//        Comment from Galaa12
    }

    @Bean
    public MapperFactory mapperFactory(){
        return new DefaultMapperFactory.Builder().build();
    }

}

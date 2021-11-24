package top.hf.hadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.hf.hadmin.repository")
public class HAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(HAdminApplication.class, args);
    }

}



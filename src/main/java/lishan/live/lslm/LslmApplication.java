package lishan.live.lslm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LslmApplication {
    public static void main(String[] args) {
        SpringApplication.run(LslmApplication.class, args);
    }
}

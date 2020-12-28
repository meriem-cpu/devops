package tn.esprit.arctic.reservation.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "tn.esprit.arctic.reservation.*")
@EnableJpaRepositories(basePackages = "tn.esprit.arctic.reservation.*")
@EntityScan(basePackages = "tn.esprit.arctic.reservation.*")
public class ReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservationApplication.class,args);
    }
}

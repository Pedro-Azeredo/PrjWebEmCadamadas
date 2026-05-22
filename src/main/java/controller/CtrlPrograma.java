package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"controller", "service", "model"})
@EnableJpaRepositories(basePackages = "model.repository")
@EntityScan(basePackages = "model")
public class CtrlPrograma {
	public static void main(String[] args) {		
        org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder enc =
                new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

        //System.out.println(enc.encode("lasalle"));
            
		SpringApplication.run(CtrlPrograma.class, args);
	}
}

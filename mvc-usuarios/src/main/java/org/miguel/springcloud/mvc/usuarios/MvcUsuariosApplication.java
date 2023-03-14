package org.miguel.springcloud.mvc.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MvcUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcUsuariosApplication.class, args);
	}

}

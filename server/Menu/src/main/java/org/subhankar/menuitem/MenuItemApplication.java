package org.subhankar.menuitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MenuItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenuItemApplication.class, args);
	}

}

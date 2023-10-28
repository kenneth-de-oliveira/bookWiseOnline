package io.github.kenneth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class CloudGatewayapplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayapplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/students/**").uri("lb://students"))
                .route(r -> r.path("/reservations/**").uri("lb://booksInventory"))
                .route(r -> r.path("/books/**", "/categories/**").uri("lb://books"))
                .build();
    }

}

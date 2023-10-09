package crisalis.blue;

;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

@SpringBootApplication
public class DemoCrisalisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCrisalisApplication.class, args);
	}

	/*@Bean
	public CorsFilter corsFilter(){
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOriginPatterns(Arrays.asList("*"));
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration( "/**", config);
		return new CorsFilter(source);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			CustomerRepository customerRepository,
			OrderRepository orderRepository,
			OrderDetailRepository orderDetailRepository,
			ProductRepository productRepository
	){
		return args -> {
			Product product = productRepository
					.save(
							new Product(null,
									"Celular 2",
									BigDecimal.valueOf(15000),
									null,
									null)
									
					);
			
			Product product2 = productRepository
					.save(
							new Product(null,
									"Tablet",
									BigDecimal.valueOf(55000),
									null,
									null)
					);
			
			Customer customer = customerRepository
					.save(
							new Customer(
									null,
									"Cliente Ejemplo",
									"40321234")
					);
			
					Order order = orderRepository
							.save(
									new Order(
											null,
											"Pedido de venta1",
											new Date(),
											customer,
											null
									)
							);

			OrderDetail orderDetail = orderDetailRepository
					.save(
							new OrderDetail(null,
									product.getPrice(),
									4.0,
									product,
									null)
					);
			
			OrderDetail orderDetail2 = orderDetailRepository
					.save(
							new OrderDetail(null,
									product.getPrice(),
									1.0,
									product2,
									null)
					);
			
			order.setOrderDetails(
					Set.of(
							orderDetail,
							orderDetail2
					)
			);
			
			orderRepository.save(order);
			
		};
	}*/
}

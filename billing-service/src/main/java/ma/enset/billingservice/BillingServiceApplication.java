package ma.enset.billingservice;

import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.entities.ProductItem;
import ma.enset.billingservice.entities.enums.BillStatus;
import ma.enset.billingservice.fein.CustomerRestClient;
import ma.enset.billingservice.fein.ProductItemRestClient;
import ma.enset.billingservice.model.Customer;
import ma.enset.billingservice.model.Product;
import ma.enset.billingservice.repositories.BillRepository;
import ma.enset.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClient customerRestClient,
							ProductItemRestClient productItemRestClient){
	return args -> {
		List<Customer> customers = customerRestClient.getAllCustomers().getContent().stream().toList();
		List<Product> products = productItemRestClient.pageProducts().getContent().stream().toList();
		Long customerId=1L;
		Random random = new Random();
		Customer customer = customerRestClient.findCustomerById(1L);
		for (int i=0 ; i<20 ; i++){
			Bill bill = Bill.builder()
					.billingDate(new Date())
					.customerId(customers.get(random.nextInt(customers.size())).getId())
					.status(Math.random()>0.6? BillStatus.PAID: BillStatus.NOT_PAID)
					.build();
			Bill savedBill = billRepository.save(bill);
			for (int j=0 ; j<products.size(); j++){
				if (Math.random()<0.1){
					ProductItem productItem = ProductItem.builder()
							.price(products.get(j).getPrice())
							.quantity(1+ random.nextInt(100))
							.bill(savedBill)
							.productId(products.get(j).getId())
							.discount(Math.random())
							.build();
					productItemRepository.save(productItem);
				}

			}
		}



		/*Customer customer = customerRestClient.findCustomerById(1L);
		Bill bill = Bill.builder()
				.billingDate(new Date())
				.customerId(customer.getId())
				.status(BillStatus.PAID)
				.build();
		Bill savedBill = billRepository.save(bill);
		PagedModel<Product> productPagedModel = productItemRestClient.pageProducts();
		productPagedModel.forEach(p->{
			ProductItem productItem = ProductItem.builder()
					.price(p.getPrice())
					.quantity(1+ new Random().nextInt(100))
					.bill(savedBill)
					.productId(p.getId())
					.discount(0.2)
					.build();
			productItemRepository.save(productItem);
		});*/
	};
	}

}

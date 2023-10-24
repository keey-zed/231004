package ma.enset.d231007billingservice;

import ma.enset.d231007billingservice.entities.Bill;
import ma.enset.d231007billingservice.entities.ProductItem;
import ma.enset.d231007billingservice.model.Customer;
import ma.enset.d231007billingservice.model.Product;
import ma.enset.d231007billingservice.repositories.BillRepository;
import ma.enset.d231007billingservice.repositories.ProductItemRepository;
import ma.enset.d231007billingservice.feign.CustomerRestClient;
import ma.enset.d231007billingservice.feign.ProductItemRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductItemRestClient productItemRestClient){
        return args -> {
            Collection<Product> products = productItemRestClient.getProducts().getContent();
            Long customerId = 1L;
            Customer customer = customerRestClient.getCustomerById(customerId);
            if(customer == null) throw new RuntimeException("Customer not found");
            Bill bill = new Bill();
            bill.setBillingDate(new Date());
            bill.setCustomerId(customerId);
            Bill savedBill = billRepository.save(bill);
            products.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setBill(savedBill);
                productItem.setProductId(product.getId());
                productItem.setQuantity(1 + new Random().nextInt(10));
                productItem.setPrice(product.getPrice());
                productItemRepository.save(productItem);
            });

        };
    }
}

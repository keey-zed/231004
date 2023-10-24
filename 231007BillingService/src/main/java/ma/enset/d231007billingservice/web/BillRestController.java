package ma.enset.d231007billingservice.web;

import ma.enset.d231007billingservice.entities.Bill;
import ma.enset.d231007billingservice.repositories.BillRepository;
import ma.enset.d231007billingservice.repositories.ProductItemRepository;
import ma.enset.d231007billingservice.feign.CustomerRestClient;
import ma.enset.d231007billingservice.feign.ProductItemRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(pi -> {
            //pi.setProduct(productItemRestClient.getProductById(pi.getProductId()));
            pi.setProductName(pi.getProductName());
        });
        return bill;
    }
}
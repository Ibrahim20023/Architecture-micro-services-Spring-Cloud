/*
package ma.enset.inventoryservice.web;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.repositories.ProductRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductRestController {
    private ProductRepository productRepository;
    public ProductRestController(ProductRepository customerRepository) {
        this.productRepository = customerRepository;
    }
    @GetMapping("/products")
    public List<Product> customerList(){
        return productRepository.findAll();
    }
    @GetMapping("/products/{id}")
    public Product customerById(@PathVariable Long id){
        return productRepository.findById(id).get();
    }
}*/

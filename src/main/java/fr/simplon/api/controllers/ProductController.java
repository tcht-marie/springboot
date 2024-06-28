
package fr.simplon.api.controllers;

import fr.simplon.api.models.Product;
import fr.simplon.api.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{productId}")
    public Optional<Product> getOneProduct(@PathVariable Integer productId) {
        return productRepository.findById(productId);
    }

    @PostMapping("/")
        public Product createProduct(@RequestBody Product product) {
            return productRepository.save(product);
        }

    @DeleteMapping("/{productId}")
        public void deleteProductById(@PathVariable Integer productId) {
            productRepository.findById(productId);
            productRepository.deleteById(productId);
    }

}

package fr.simplon.api.controllers;

import fr.simplon.api.messageDTO.MessageBecomesOrder;
import fr.simplon.api.messageDTO.MessageCreateCart;
import fr.simplon.api.messageDTO.MessageUpdateCart;
import fr.simplon.api.models.Cart;
import fr.simplon.api.models.CartState;
import fr.simplon.api.models.Product;
import fr.simplon.api.models.User;
import fr.simplon.api.repositories.CartRepository;
import fr.simplon.api.repositories.ProductRepository;
import fr.simplon.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartController(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    @PostMapping("/")
    public Cart createCart(@RequestBody MessageCreateCart cart) {
        Cart newCart = new Cart();
        User user = userRepository.findById(cart.getUser()).get();
        newCart.setUser(user);
        Product product = productRepository.findById(cart.getProduct()).get();
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(product);
        newCart.setProducts(listProduct);
        newCart.setCreationDate(LocalDateTime.now());
        return cartRepository.save(newCart);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Integer id, @RequestBody MessageUpdateCart cart) throws Exception {
        Cart updateCart = cartRepository.findById(cart.getCart()).get();
        if (updateCart.getState() == CartState.CART) {
            Product product = productRepository.findById(cart.getProduct()).get();
            updateCart.getProducts().add(product);
            cartRepository.save(updateCart);
            return updateCart;
        } else {
            throw new Exception("La modification n'est pas permise");
        }
    }

    @GetMapping("/{id}/order")
    public Cart becomesOrder(@PathVariable Integer id, @RequestBody MessageBecomesOrder cart){
        Cart becomesOrder = cartRepository.findById(cart.getCart()).get();
        becomesOrder.setState(CartState.ORDER);
        cartRepository.save(becomesOrder);
        return becomesOrder;
    }

}

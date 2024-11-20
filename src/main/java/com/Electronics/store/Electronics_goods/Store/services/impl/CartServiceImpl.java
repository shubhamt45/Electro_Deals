package com.Electronics.store.Electronics_goods.Store.services.impl;

import com.Electronics.store.Electronics_goods.Store.dtos.AddItemToCartRequest;
import com.Electronics.store.Electronics_goods.Store.dtos.CartDto;
import com.Electronics.store.Electronics_goods.Store.entities_models.Cart;
import com.Electronics.store.Electronics_goods.Store.entities_models.CartItem;
import com.Electronics.store.Electronics_goods.Store.entities_models.Product;
import com.Electronics.store.Electronics_goods.Store.entities_models.User;
import com.Electronics.store.Electronics_goods.Store.exceptions.BadApiRequestException;
import com.Electronics.store.Electronics_goods.Store.exceptions.ResourceNotFoundException;
import com.Electronics.store.Electronics_goods.Store.repositories.CartItemRepository;
import com.Electronics.store.Electronics_goods.Store.repositories.CartRepository;
import com.Electronics.store.Electronics_goods.Store.repositories.ProductRepository;
import com.Electronics.store.Electronics_goods.Store.repositories.UserRepository;
import com.Electronics.store.Electronics_goods.Store.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {

        int quantity = request.getQuantity();
        String productId = request.getProductId();

        if (quantity <= 0) {
            throw new BadApiRequestException("Requested quantity is not valid !!");
        }

        //fetch the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found in database !!"));
        //fetch the user from db
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found in database!!"));

        Cart cart = null;
        try {
            cart = cartRepository.findByUser(user).get();
            // above code , is trying to retrieve the cart associated with a specific user from the database.
        } catch (NoSuchElementException e) {
            // If the cart does not exist (i.e., no cart is found for the user), it creates a new cart with a unique ID and a creation date.
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }

        //perform cart operations
        //if cart items already present; then update
        //The code is checking whether the product the user wants to add to the cart already exists in the cart.
        // If it does, the code updates the quantity and price of that product in the cart.
        //if the product in the cart is not there , then the new cart iss created.
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();
        items = items.stream().map(item -> {

            if (item.getProduct().getProductId().equals(productId)) {
                //item already present in cart
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getDiscountedPrice());
                updated.set(true);
            }
            return item;
        }).collect(Collectors.toList());

//        cart.setItems(updatedItems);

        //create items
        if (!updated.get()) {
            CartItem cartItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getDiscountedPrice())
                    .cart(cart)
                    .product(product)
                    .build();
            cart.getItems().add(cartItem);
        }


        cart.setUser(user);
        Cart updatedCart = cartRepository.save(cart);
        return mapper.map(updatedCart, CartDto.class);


    }

    @Override
    public void removeItemFromCart(String userId, int cartItem) {
        //conditions
        CartItem cartItem1 = cartItemRepository.findById(cartItem).orElseThrow(() -> new ResourceNotFoundException("Cart Item not found !!"));
        cartItemRepository.delete(cartItem1);
    }

    @Override
    public void clearCart(String userId) {
        //fetch the user from db
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found in database!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart of given user not found !!"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found in database!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart of given user not found !!"));

        return mapper.map(cart, CartDto.class);
    }
}

package com.Electronics.store.Electronics_goods.Store.repositories;

import com.Electronics.store.Electronics_goods.Store.entities_models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
}

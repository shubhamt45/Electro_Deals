package com.Electronics.store.Electronics_goods.Store.repositories;

import com.Electronics.store.Electronics_goods.Store.entities_models.Order;
import com.Electronics.store.Electronics_goods.Store.entities_models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByUser(User user);
}

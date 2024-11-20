package com.Electronics.store.Electronics_goods.Store.repositories;

import com.Electronics.store.Electronics_goods.Store.entities_models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRespository extends JpaRepository<Order, String> {
}

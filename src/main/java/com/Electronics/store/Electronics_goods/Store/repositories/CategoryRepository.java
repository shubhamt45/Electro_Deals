package com.Electronics.store.Electronics_goods.Store.repositories;

import com.Electronics.store.Electronics_goods.Store.entities_models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}

package com.Electronics.store.Electronics_goods.Store.repositories;

import com.Electronics.store.Electronics_goods.Store.entities_models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
//In repository layer interface is use instead of class in order to ensure the lose coupling


    //why below things are written inside ok
    Optional<User> findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    List<User> findByNameContaining(String keywords);
}

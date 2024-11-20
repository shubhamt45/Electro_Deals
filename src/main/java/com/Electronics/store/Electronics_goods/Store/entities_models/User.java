package com.Electronics.store.Electronics_goods.Store.entities_models;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    private String userId;

    @Column(name ="user_name")
    private String name;

    @Column(name ="user_email",unique=true)
    private String email;

    @Column(name ="user_password",length = 500)
    private String password;
    private String gender;

    @Column(length = 1000)
    private String about;

    @Column(name = "user_image_name")
    private String imageName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();
}

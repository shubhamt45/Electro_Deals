package com.Electronics.store.Electronics_goods.Store.services;

import com.Electronics.store.Electronics_goods.Store.dtos.PageableResponse;
import com.Electronics.store.Electronics_goods.Store.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //create
    UserDto createUser(UserDto userDto);


    //update
    UserDto updateUser(UserDto userDto,String userId);

    //delete
    void deleteUser(String userId);

    //get  all user
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

//    List<UserDto> getAllUser();
    //get single  user by id
    UserDto getUserById(String userId);


    //get user by email id
    UserDto getUserByEmail(String email);


    //search user
    List<UserDto> searchUser(String keyword);




    //other user specific features
//    Optional<User> findUserByEmailOptional(String email);

}

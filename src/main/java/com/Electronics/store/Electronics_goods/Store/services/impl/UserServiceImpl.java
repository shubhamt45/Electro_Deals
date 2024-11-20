package com.Electronics.store.Electronics_goods.Store.services.impl;


import com.Electronics.store.Electronics_goods.Store.dtos.PageableResponse;
import com.Electronics.store.Electronics_goods.Store.dtos.UserDto;
import com.Electronics.store.Electronics_goods.Store.entities_models.User;
import com.Electronics.store.Electronics_goods.Store.exceptions.ResourceNotFoundException;
import com.Electronics.store.Electronics_goods.Store.helper.Helper;
import com.Electronics.store.Electronics_goods.Store.repositories.UserRepository;
import com.Electronics.store.Electronics_goods.Store.services.UserService;
//import org.hibernate.query.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

//import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

//    @Value("${user.profile.image.path}")
//    private String


    @Override
    public UserDto createUser(UserDto userDto) {
        //dto to Entity
        User user = dtoToEntity(userDto);

        // Manually generate and set a UUID for the userId
        user.setUserId(UUID.randomUUID().toString());  // Generating a unique userId

        //below line is saving the data in the database.
        User savedUser = userRepository.save(user);
        //why these userRepository interface is used here ok

        //entity -> dto
        UserDto newDto = entityToDto(savedUser);
        //Agaar maaine table mai data store kiya hai toh use , why Im converting back to dto ?
        //upon conversion do the data remains store in the table
        return newDto;

        //ok when dto -> entity conversion happens at that time do the  dto gets empty
    }



    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id !!"));
        user.setName(userDto.getName());

        //email update
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        //savedata
       User updatedUser = userRepository.save(user);
       UserDto updatedDto = entityToDto(updatedUser);
        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id !!"));
        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

         Pageable pageable = PageRequest.of(pageNumber,pageSize);
         Page<User> page = userRepository.findAll(pageable);

        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);

        return response;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id !!"));
        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with given email id !!"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;

    }



    private UserDto entityToDto(User savedUser) {
//                UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName())
//                .build();
        return mapper.map(savedUser,UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {
//                User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();
//        return null;
        return mapper.map(userDto,User.class);
    }
}

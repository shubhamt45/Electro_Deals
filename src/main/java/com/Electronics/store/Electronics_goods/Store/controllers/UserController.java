package com.Electronics.store.Electronics_goods.Store.controllers;

import com.Electronics.store.Electronics_goods.Store.dtos.ApiResponseMessage;
import com.Electronics.store.Electronics_goods.Store.dtos.ImageResponse;
import com.Electronics.store.Electronics_goods.Store.dtos.PageableResponse;
import com.Electronics.store.Electronics_goods.Store.dtos.UserDto;
import com.Electronics.store.Electronics_goods.Store.services.FileService;
import com.Electronics.store.Electronics_goods.Store.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //JSON to UserDto conversion happens with the help of the Jackson library

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>  updateUser(
            @PathVariable("userId") String userId,
          @Valid  @RequestBody UserDto userDto
    ) {
        UserDto updatedUserDto = userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);

//        ApiResponseMessage message = new ApiResponseMessage();
//        message.setMessage("User deleted");    // The message to indicate the result
//        message.setSuccess(true);              // Indicating the operation was successful
//        message.setStatus(HttpStatus.OK);      // HTTP 200 OK status

        ApiResponseMessage message = ApiResponseMessage.builder()
                                    .message("User deleted")
                                    .success(true)
                                    .status(HttpStatus.OK)
                                    .build();
        return new ResponseEntity<>( message, HttpStatus.OK);
    }

    //get ALl
    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        return new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    //getSingle
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    //getEmail
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserById(email),HttpStatus.OK);
    }

    //search user
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords) {
        return new ResponseEntity<>(userService.searchUser(keywords),HttpStatus.OK);
    }

//below  both methods are remaining for debugging
    //upload user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage") MultipartFile image, @PathVariable String userId) throws IOException {
        String imageName = fileService.uploadFile(image, imageUploadPath);

        //below line is setting the particular user id
        UserDto user = userService.getUserById(userId);
        user.setImageName(imageName);
        //This line sets the image name (which was generated during the file upload) in the user’s profile.
        //It associates the uploaded image with this particular user ok,
        //uploading :profile picture on whtsapp,Insta ,LinkedIn etc

        UserDto userDto = userService.updateUser(user, userId);
        //in above line updated user data is stored in the data base snd then reflected back to the user.

        ImageResponse imageResponse = ImageResponse.builder()
                .imageName(imageName)
                .success(true)
                .message("image is uploaded successfully ")
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

    }

    //serve user image

    @GetMapping(value = "/image/{userId}")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        UserDto user = userService.getUserById(userId);
        logger.info("User image name : {} ", user.getImageName());
        InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());

    }


}

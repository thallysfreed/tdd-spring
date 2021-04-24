package com.silth.wallet.controller;

import javax.validation.Valid;

import com.silth.wallet.dto.UserDTO;
import com.silth.wallet.entity.User;
import com.silth.wallet.response.Response;
import com.silth.wallet.service.UserService;
import com.silth.wallet.util.BCrypt;
import com.silth.wallet.util.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Response<UserDTO>> create(@Valid @RequestBody UserDTO dto, BindingResult result){
        Response<UserDTO> response = new Response<>();

        if(result.hasErrors()){
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User user = userService.save(this.convertDtoToEntity(dto));

        response.setData(this.convertEntityToDto(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private User convertDtoToEntity(UserDTO dto){
        User u = new User();
        u.setId(dto.getId());
        u.setPassword(BCrypt.getHash(dto.getPassword()));
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        u.setRole(RoleEnum.valueOf(dto.getRole()));

        return u;
    }

    private UserDTO convertEntityToDto(User user){
        UserDTO u = new UserDTO();
        u.setId(user.getId());
        u.setName(user.getName());
        u.setEmail(user.getEmail());

        return u;
    }
}

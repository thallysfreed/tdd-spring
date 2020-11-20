package com.silth.wallet.controller;

import com.silth.wallet.dto.UserWalletDTO;
import com.silth.wallet.entity.User;
import com.silth.wallet.entity.UserWallet;
import com.silth.wallet.entity.Wallet;
import com.silth.wallet.response.Response;
import com.silth.wallet.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-wallet")
public class UserWalletController {
    @Autowired
    private UserWalletService service;

    @PostMapping
    public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO dto, BindingResult result) {

        Response<UserWalletDTO> response = new Response<UserWalletDTO>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }

        UserWallet uw = service.save(this.convertDtoToEntity(dto));

        response.setData(this.convertEntityToDto(uw));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public UserWallet convertDtoToEntity(UserWalletDTO dto) {
        UserWallet userWallet = new UserWallet();
        User u = new User();
        u.setId(dto.getUsers());
        Wallet w = new Wallet();
        w.setId(dto.getWallet());

        userWallet.setId(dto.getId());
        userWallet.setUsers(u);
        userWallet.setWallet(w);

        return userWallet;
    }

    public UserWalletDTO convertEntityToDto(UserWallet uw) {
        UserWalletDTO userWalletDTO = new UserWalletDTO();
        userWalletDTO.setId(uw.getId());
        userWalletDTO.setUsers(uw.getUsers().getId());
        userWalletDTO.setWallet(uw.getWallet().getId());

        return userWalletDTO;
    }
}

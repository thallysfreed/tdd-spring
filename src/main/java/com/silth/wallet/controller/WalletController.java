package com.silth.wallet.controller;

import com.silth.wallet.dto.WalletDTO;
import com.silth.wallet.entity.Wallet;
import com.silth.wallet.response.Response;
import com.silth.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @PostMapping
    public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO walletDTO, BindingResult result){
        Response<WalletDTO> response = new Response<>();

        if(result.hasErrors()){
            if(result.hasErrors()){
                result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }

        Wallet wallet = walletService.save(convertDtoToEntity(walletDTO));

        response.setData(convertEntityToDTO(wallet));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private Wallet convertDtoToEntity(WalletDTO walletDTO){
        Wallet wallet = new Wallet();

        wallet.setId(walletDTO.getId());
        wallet.setName(walletDTO.getName());
        wallet.setValue(walletDTO.getValue());

        return wallet;
    }

    private WalletDTO convertEntityToDTO(Wallet wallet){
        WalletDTO walletDTO = new WalletDTO();

        walletDTO.setId(wallet.getId());
        walletDTO.setName(wallet.getName());
        walletDTO.setValue(wallet.getValue());

        return walletDTO;
    }
}

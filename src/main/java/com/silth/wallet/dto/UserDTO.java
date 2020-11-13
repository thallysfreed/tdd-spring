package com.silth.wallet.dto;

import lombok.Data;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Data
public class UserDTO {
    private Long id;

    @Length(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
    private String name;

    @NotNull
    @Length(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    private String password;

    private String email;
}

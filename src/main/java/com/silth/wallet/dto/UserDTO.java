package com.silth.wallet.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    private Long id;

    @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
    private String name;

    @NotNull
    @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    private String password;

    @Email(message = "Email inválido")
    private String email;
}

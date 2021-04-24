package com.silth.wallet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.silth.wallet.entity.Wallet;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class WalletItemDTO {
    private Long id;

    @NotNull(message = "Carteira obrigatória")
    private Long walletId;

    @NotNull(message = "Data obrigatória")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private Date date;

    @Column(nullable = false)
    @NotNull(message = "Tipo obrigatório")
    @Pattern(regexp = "^(ENTRADA|SAIDA)$", message = "Aceita somente ENTRADA|SAIDA")
    private String type;

    @Column(nullable = false)
    @NotNull(message = "Descrição obrigatória")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Valor obrigatório")
    private BigDecimal value;
}

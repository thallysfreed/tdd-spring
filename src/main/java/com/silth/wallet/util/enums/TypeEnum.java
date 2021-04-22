package com.silth.wallet.util.enums;

public enum TypeEnum {
    EN("ENTRADA"),
    SD("SAIDA");

    private final String value;

    TypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static TypeEnum getEnum(String value){
        for(TypeEnum typeEnum: values()){
            if(typeEnum.equals(value)){
                return typeEnum;
            }
        }
        return null;
    }
}

package com.silth.wallet.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Crypt {
    public static String getHash(String password){
        if(password == null){
            return null;
        }

        BCryptPasswordEncoder bCryptPasswordEnconder = new BCryptPasswordEncoder ();
        return bCryptPasswordEnconder.encode(password);
    }
}

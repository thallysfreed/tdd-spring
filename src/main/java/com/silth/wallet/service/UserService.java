package com.silth.wallet.service;

import com.silth.wallet.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User u);

    Optional<User> findByEmail(String email);
}

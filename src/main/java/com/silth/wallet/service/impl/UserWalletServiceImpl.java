package com.silth.wallet.service.impl;

import com.silth.wallet.entity.UserWallet;
import com.silth.wallet.repository.UserWalletRepository;
import com.silth.wallet.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserWalletServiceImpl implements UserWalletService {
    @Autowired
    UserWalletRepository repository;

    @Override
    public UserWallet save(UserWallet uw) {
        return repository.save(uw);
    }

    @Override
    public Optional<UserWallet> findByUsersIdAndWalletId(Long user, Long wallet) {
        return repository.findByUsersIdAndWalletId(user, wallet);
    }
}

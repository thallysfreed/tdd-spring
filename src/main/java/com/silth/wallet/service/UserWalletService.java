package com.silth.wallet.service;

import com.silth.wallet.entity.UserWallet;

import java.util.Optional;

public interface UserWalletService {

    UserWallet save(UserWallet uw);

    Optional<UserWallet> findByUsersIdAndWalletId(Long user, Long wallet);
}

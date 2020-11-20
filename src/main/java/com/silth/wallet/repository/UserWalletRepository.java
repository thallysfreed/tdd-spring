package com.silth.wallet.repository;

import com.silth.wallet.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserWalletRepository  extends JpaRepository<UserWallet, Long> {

    Optional<UserWallet> findByUsersIdAndWalletId(Long user, Long wallet);
}
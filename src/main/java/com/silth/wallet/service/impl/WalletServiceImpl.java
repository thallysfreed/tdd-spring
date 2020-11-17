package com.silth.wallet.service.impl;

import com.silth.wallet.entity.Wallet;
import com.silth.wallet.repository.WalletRepository;
import com.silth.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}

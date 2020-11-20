package com.silth.wallet.service;


import com.silth.wallet.entity.User;
import com.silth.wallet.entity.UserWallet;
import com.silth.wallet.entity.Wallet;
import com.silth.wallet.repository.UserWalletRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserWalletServiceTest {
    public static final long WALLET_ID = 1l;
    @MockBean
    private UserWalletRepository userWalletRepository;

    @Autowired
    private UserWalletService userWalletService;

    @Test
    public void testeSaveUserWallet(){
        UserWallet userWallet = new UserWallet();
        Mockito.when(userWalletRepository.save(Mockito.any(UserWallet.class))).thenReturn(getUserWallet());

        UserWallet userWalletSaved = userWalletService.save(userWallet);

        Assert.assertNotNull(userWalletSaved);
    }

    @Test
    public void testFindUser(){
        UserWallet userWallet = new UserWallet();
        Mockito.when(userWalletRepository.findByUsersIdAndWalletId(Mockito.any(Long.class), Mockito.any(Long.class))).thenReturn(getOptionalUserWallet());

        Optional<UserWallet> userWalletReturned = userWalletService.findByUsersIdAndWalletId(0l, 0l);

        Assert.assertNotNull(userWalletReturned);
        Assert.assertTrue(userWalletReturned.isPresent());
        Assert.assertTrue(userWalletReturned.isPresent());
    }

    private User getUser(){
        User user = new User();
        user.setId(0l);
        user.setName("TESTE");
        user.setEmail("teste@teste.com.br");
        user.setPassword("123456");
        return user;
    }

    private Wallet getWallet(){
        Wallet wallet = new Wallet();
        wallet.setId(0l);
        wallet.setName("CARTEIRA DE TESTE");
        wallet.setValue(new BigDecimal(50));
        return wallet;
    }

    private UserWallet getUserWallet(){
        UserWallet userWallet = new UserWallet();
        userWallet.setWallet(getWallet());
        userWallet.setUsers(getUser());
        userWallet.setId(WALLET_ID);
        return userWallet;
    }

    private Optional<UserWallet> getOptionalUserWallet(){
        UserWallet userWallet = new UserWallet();
        userWallet.setWallet(getWallet());
        userWallet.setUsers(getUser());
        userWallet.setId(WALLET_ID);

        return Optional.of(userWallet);
    }

}

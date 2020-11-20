package com.silth.wallet.repository;

import com.silth.wallet.entity.User;
import com.silth.wallet.entity.UserWallet;
import com.silth.wallet.entity.Wallet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserWalletRepositoryTest {

    @Autowired
    private UserWalletRepository userWalletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Before
    public void setup(){

    }

    @After
    public void tearDown(){
        userWalletRepository.deleteAll();
        userRepository.deleteAll();
        walletRepository.deleteAll();
    }

    @Test
    public void testSaveRepository(){
        UserWallet userWallet = new UserWallet();
        userWallet.setId(1L);
        userWallet.setUsers(getUser());
        userWallet.setWallet(getWallet());

        UserWallet userWalletResult = userWalletRepository.save(userWallet);

        Assert.assertNotNull(userWalletResult);
    }

    @Test
    public void testFindByUserIdAndWalletId(){
        UserWallet userWallet = new UserWallet();
        userWallet.setId(1L);
        userWallet.setUsers(getUser());
        userWallet.setWallet(getWallet());

        UserWallet userWalletSaved = userWalletRepository.save(userWallet);

        Optional<UserWallet> userWalletResult = userWalletRepository.findByUsersIdAndWalletId(userWalletSaved.getUsers().getId(),userWalletSaved.getWallet().getId());

        Assert.assertNotNull(userWalletResult);
        Assert.assertTrue(userWalletResult.isPresent());
        Assert.assertTrue(userWalletResult.get().getUsers().getId().equals(userWalletSaved.getUsers().getId()));
        Assert.assertTrue(userWalletResult.get().getWallet().getId().equals(userWalletSaved.getWallet().getId()));
    }

    private User getUser(){
        User user = new User();
        user.setId(0l);
        user.setName("TESTE");
        user.setEmail("teste@teste.com.br");
        user.setPassword("123456");
        return userRepository.save(user);
    }

    private Wallet getWallet(){
        Wallet wallet = new Wallet();
        wallet.setId(0l);
        wallet.setName("CARTEIRA DE TESTE");
        wallet.setValue(new BigDecimal(50));
        return walletRepository.save(wallet);
    }
}

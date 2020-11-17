package com.silth.wallet.repository;

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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletRepositoryTest {

    private static String WALLET_NAME = "Carteira1";

    @Autowired
    private WalletRepository walletRepository;

    @After
    public void tearDown(){
        walletRepository.deleteAll();
    }

    @Test
    public void testSaveWallet(){
        Wallet wallet = new Wallet();
        wallet.setValue(new BigDecimal(10));
        wallet.setName("Carteira Tal");
        wallet.setId(1l);

        Wallet response = walletRepository.save(wallet);

        Assert.assertNotNull(response);
    }

    @Test
    public void testFindByName(){
        Wallet wallet = new Wallet();
        wallet.setValue(new BigDecimal(10));
        wallet.setName(WALLET_NAME);
        wallet.setId(1l);

        Wallet response = walletRepository.save(wallet);
        Optional<Wallet> responseFind = walletRepository.findByName(WALLET_NAME);
        Assert.assertNotNull(response);
        Assert.assertTrue(responseFind.isPresent());
        Assert.assertThat(responseFind.get().getName(), equalTo(WALLET_NAME));

    }

}

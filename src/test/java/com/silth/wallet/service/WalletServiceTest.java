package com.silth.wallet.service;

import com.silth.wallet.entity.Wallet;
import com.silth.wallet.repository.WalletRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletServiceTest {

    @MockBean
    private WalletRepository walletRepository;

    @Autowired
    private WalletService walletService;

    @Test
    public void testSaveWallet(){
        Wallet wallet = new Wallet();
        wallet.setId(1L);
        wallet.setName("Teste");
        wallet.setValue(new BigDecimal(100));

        Mockito.when(walletRepository.save(wallet)).thenReturn(new Wallet());

        Wallet wallet1 = walletService.save(wallet);

        Assert.assertNotNull(wallet1);
    }


}

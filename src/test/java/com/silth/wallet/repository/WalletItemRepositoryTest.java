package com.silth.wallet.repository;

import com.silth.wallet.entity.Wallet;
import com.silth.wallet.entity.WalletItem;
import com.silth.wallet.util.enums.TypeEnum;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletItemRepositoryTest {
    private static final Date DATE = new Date();
    private static final TypeEnum TYPE = TypeEnum.EN;
    private static final String DESCRIPTION = "Conta de Internet";
    private static final BigDecimal VALUE = BigDecimal.valueOf(135);
    private Long idWalletSaved;
    private Long idWalletItemSaved;

    @Autowired
    WalletItemRepository walletItemRepository;

    @Autowired
    WalletRepository walletRepository;

    @Before
    public void setup(){
        Wallet wallet =new Wallet();
        wallet.setName("Carteira 1");
        wallet.setValue(BigDecimal.valueOf(500));

        idWalletSaved = walletRepository.save(wallet).getId();

        WalletItem walletItem = new WalletItem(1L, wallet, DATE, TYPE, DESCRIPTION, VALUE);

        idWalletItemSaved = walletItemRepository.save(walletItem).getId();
    }

    @After
    public void tearDown(){
        walletItemRepository.deleteAll();
        walletRepository.deleteAll();
    }

    @Test
    public void testSave(){
        Wallet wallet =new Wallet();
        wallet.setName("Carteira 1");
        wallet.setValue(BigDecimal.valueOf(500));

        walletRepository.save(wallet);

        WalletItem walletItem = new WalletItem(1L, wallet, DATE, TYPE, DESCRIPTION, VALUE);

        WalletItem response = walletItemRepository.save(walletItem);

        Assert.assertNotNull(response);
        Assert.assertEquals(DATE, response.getDate());
        Assert.assertEquals(TYPE, response.getType());
        Assert.assertEquals(DESCRIPTION, response.getDescription());
        Assert.assertEquals(VALUE, response.getValue());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testarException(){
        WalletItem walletItem = new WalletItem(null, null, DATE, null, DESCRIPTION, null);
        walletItemRepository.save(walletItem);
    }
}

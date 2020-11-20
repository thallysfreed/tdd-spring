package com.silth.wallet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silth.wallet.dto.UserWalletDTO;
import com.silth.wallet.dto.WalletDTO;
import com.silth.wallet.entity.User;
import com.silth.wallet.entity.UserWallet;
import com.silth.wallet.entity.Wallet;
import com.silth.wallet.service.UserWalletService;
import com.silth.wallet.service.WalletService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserWalletControllerTest {
    public static final long WALLET_ID = 1l;
    public static final long USER_ID = 1l;
    public static final String URL = "/wallet";

    @MockBean
    private UserWalletService userWalletService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup(){
        BDDMockito.given(userWalletService.save(Mockito.any(UserWallet.class))).willReturn(getUserWallet());
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testSaveUserWallet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(USER_ID, WALLET_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.data.users").value(USER_ID))
                .andExpect(jsonPath("$.data.wallet").value(WALLET_ID));
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

    public String getJsonPayload(Long userId, Long walletId) throws JsonProcessingException {
        UserWalletDTO dto = new UserWalletDTO();
        dto.setId(1l);
        dto.setUsers(userId);
        dto.setWallet(walletId);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dto);
    }
}

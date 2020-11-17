package com.silth.wallet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silth.wallet.dto.WalletDTO;
import com.silth.wallet.entity.Wallet;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WalletControllerTest {
    private static final String URL = "/wallet";

    private static final Long ID =1L;
    private static final String NAME = "WALLET1";
    private static final BigDecimal VALUE = new BigDecimal(10);


    @MockBean
    private WalletService walletService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup(){
        BDDMockito.given(walletService.save(Mockito.any(Wallet.class))).willReturn(getMockWallet());
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testSaveWallet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME, VALUE))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.name").value(NAME))
                .andExpect(jsonPath("$.data.value").value(VALUE));
    }

    @Test
    public void testValidationNameError() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, "", VALUE))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).header("Accept-Encoding","br"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("length must be between 3 and 2147483647"));

    }

    public Wallet getMockWallet(){
        Wallet wallet = new Wallet();
        wallet.setValue(VALUE);
        wallet.setName(NAME);
        wallet.setId(ID);

        return wallet;
    }

    public String getJsonPayload(Long id, String name, BigDecimal value) throws JsonProcessingException {
        WalletDTO dto = new WalletDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setValue(value);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dto);
    }
}

package com.spring.login;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.peluqueria.config.BezkoderProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BezkoderPropertiesIntegrationTest {

    @Autowired
    private BezkoderProperties bezkoderProperties;

    @Test
    public void whenSimplePropertyQueriedThenReturnsPropertyValue() 
      throws Exception {
        Assert.assertEquals("bezkoder", bezkoderProperties.getJwtCookieName());
    }
    
}
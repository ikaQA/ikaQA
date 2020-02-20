package jp.co.heartsoft.ikaqa.controller;

import jp.co.heartsoft.ikaqa.service.CalcService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IkaQAControllerTest {
    @InjectMocks
    private IkaQAController ikaQAController;

    @Mock
    private CalcService calcService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void executeSlashCommand() {

        when(this.calcService.add(1,2)).thenReturn(3);

        String result = this.ikaQAController.executeSlashCommand();
        assertEquals("call postMessage", result);
    }


}
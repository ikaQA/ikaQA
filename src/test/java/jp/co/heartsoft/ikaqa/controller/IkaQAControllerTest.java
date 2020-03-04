package jp.co.heartsoft.ikaqa.controller;

import jp.co.heartsoft.ikaqa.service.CalcService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class IkaQAControllerTest {
    @InjectMocks
    private IkaQAController ikaQAController;

    @Mock
    private CalcService calcService;

    @Captor
    ArgumentCaptor<Integer> aCaptor;

    @Captor
    ArgumentCaptor<Integer> bCaptor;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void executeSlashCommand() {

        when(this.calcService.add(anyInt(),anyInt())).thenReturn(5);

        int result = this.ikaQAController.executeSlashCommand();
        verify(this.calcService, times(1)).add(aCaptor.capture(),
                bCaptor.capture());

        assertEquals(1, aCaptor.getValue());
        assertEquals(3, bCaptor.getValue());
        assertEquals(5, result);
    }


}
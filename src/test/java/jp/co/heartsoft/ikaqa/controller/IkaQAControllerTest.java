package jp.co.heartsoft.ikaqa.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IkaQAControllerTest {
    private IkaQAController ikaQAController;
    @BeforeEach
    void setUp() {
        ikaQAController = new IkaQAController();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void executeSlashCommand() {
        assertEquals("call postMessageX", ikaQAController.executeSlashCommand());
    }


}
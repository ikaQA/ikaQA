package jp.co.heartsoft.ikaqa.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ikaQAApplicationTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new IkaQAController()).build();
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * slashコマンド(/ikaqa)を受け付けるIkaQACotrollerをテストする
     * ・http postを受け付ける
     * ・レスポンス確認
     * 　・ステータスコードが200であること
     */
    @Test
    void excecuteSlashCommandTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/ikaqa"))
                // レスポンスのステータスコードが200であることを検証する
                .andExpect(status().isOk());
    }


}
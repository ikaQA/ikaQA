package jp.co.heartsoft.ikaqa.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
 public class IkaQAControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new IkaQAController()).build();
    }

    @Test
    public void getIndexTest() throws Exception {

        // GETで「/」にアクセスする
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                // レスポンスのステータスコードが200であることを検証する
                .andExpect(status().isOk())
                // レスポンスボディが「Hello World」であることを検証する
                .andExpect(content().string("Hello World"));
    }

    @Test
    void postIndexTest() throws Exception {

        // POSTで「/」にアクセスする
        mockMvc.perform(MockMvcRequestBuilders.post("/"))
                // レスポンスのステータスコードが405（METHOD_NOT_ALLOWED）であることを検証する
                .andExpect(status().isMethodNotAllowed());
    }


}
package jp.co.heartsoft.ikaqa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IkaQAController {
    @PostMapping("/ikaqa")
    public String executeSlashCommand() {
        System.out.println("execute SlashCommand");
        return "call postMessage";
    }
}

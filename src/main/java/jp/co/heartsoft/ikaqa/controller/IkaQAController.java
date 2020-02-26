package jp.co.heartsoft.ikaqa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IkaQAController {
    @GetMapping("/executeSlashCommand")
    public String executeSlashCommand() {
        System.out.println("execute SlashCommand");
        return "call postMessage";
    }

    @PostMapping("/ikaqaDebug")
    public String ikaqaDebug(@RequestBody String requestBody) {
        System.out.println("requestBody = " + requestBody);
        return "ikaqaDebug(" + requestBody + ")";
    }
}

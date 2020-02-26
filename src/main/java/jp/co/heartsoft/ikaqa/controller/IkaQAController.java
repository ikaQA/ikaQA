package jp.co.heartsoft.ikaqa.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class IkaQAController {
    @GetMapping("/executeSlashCommand")
    public String executeSlashCommand() {
        System.out.println("execute SlashCommand");
        return "call postMessage";
    }

    @PostMapping("/ikaqaDebug")
    public @ResponseBody String ikaqaDebug(@RequestBody String requestBody) {
        System.out.println("requestBody = " + requestBody);
        return "ikaqaDebug(" + requestBody + ")";
    }
}

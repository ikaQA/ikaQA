package jp.co.heartsoft.ikaqa.controller;

import jp.co.heartsoft.ikaqa.service.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IkaQAController {

    @Autowired
    private CalcService calcService;

    @GetMapping("/executeSlashCommand")
    public String executeSlashCommand() {

        System.out.println(calcService.add(1,2));
        return "call postMessage";
    }
}

package jp.co.heartsoft.ikaqa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class IkaQAController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    String greeting() {
        return "Hello World";
    }
}

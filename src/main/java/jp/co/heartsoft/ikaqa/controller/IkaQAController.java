package jp.co.heartsoft.ikaqa.controller;

import jp.co.heartsoft.ikaqa.service.CalcService;
import jp.co.heartsoft.ikaqa.service.IkaQAHttpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class IkaQAController {

    @Autowired
    private CalcService calcService;

    @Autowired
    private IkaQAHttpRequestService ikaQAHttpRequestService;

    @PostMapping("/postMessage")
    public @ResponseBody String postMessage(@RequestParam("text") String text) {
        try {
            ikaQAHttpRequestService.postMessage(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @PostMapping("/postAnswer")
    public @ResponseBody String postAnswer(@RequestParam("text") String text) {


        try {
            ikaQAHttpRequestService.postMessage(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

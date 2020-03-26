package jp.co.heartsoft.ikaqa.controller;

import com.google.gson.Gson;
import jp.co.heartsoft.ikaqa.bean.SlackAnswerPayloadBean;
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

    @PostMapping("/openDialog")
    public @ResponseBody String openDialog(@RequestParam("command") String command, @RequestParam("trigger_id") String triggerId) {
        try {
            if("/ikaqa_answer".equals(command)){
                ikaQAHttpRequestService.openDialog(triggerId);
            }

            //TODO ダイアログ入力後の処理を記載
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @PostMapping("/postAnswer")
    public @ResponseBody String postAnswer(@RequestParam("payload") String payload) {
        try {
            Gson gson = new Gson();
            SlackAnswerPayloadBean Payload = gson.fromJson(payload, SlackAnswerPayloadBean.class);
            ikaQAHttpRequestService.postAnswer(Payload.getSubmission());

            //TODO ダイアログ入力後の処理を記載
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

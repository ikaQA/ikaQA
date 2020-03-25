package jp.co.heartsoft.ikaqa.service;

import com.google.gson.Gson;
import jp.co.heartsoft.ikaqa.bean.SlackDialogBean;
import jp.co.heartsoft.ikaqa.bean.SlackDialogElementBean;
import jp.co.heartsoft.ikaqa.bean.SlackDialogSubmissionBean;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class IkaQAHttpRequestService {
    @Value("${ikaqa.slack.token}")
    private String TOKEN;
    @Value("${ikaqa.slack.channel.id}")
    private String CHANNEL_ID;
    private String USER_NAME = "以下、名無しにかわりましてikaQAがお送りします";
    private String USER_ICON = ":ika:";
    private String POST_MESSAGE_URL = "https://slack.com/api/chat.postMessage";
    private String DIALOG_OPEN_URL = "https://slack.com/api/dialog.open";

    public void postMessage(String message) throws IOException {
        System.out.println("postMessage");
        Map<String, String> formParamMap = new HashMap<>();
        formParamMap.put("token", TOKEN);
        formParamMap.put("channel", CHANNEL_ID);
        formParamMap.put("text", "Q. " + message);
        formParamMap.put("username", USER_NAME);
        formParamMap.put("icon_emoji", USER_ICON);
        postHttpRequest(POST_MESSAGE_URL, formParamMap);
    }

    public void openDialog(String triggerId) throws IOException {
        System.out.println("openDialog");
        Gson gson = new Gson();
        Map<String, String> formParamMap = new HashMap<>();
        formParamMap.put("token", TOKEN);
        formParamMap.put("trigger_id", triggerId);
        formParamMap.put("dialog", gson.toJson(createDialog()));
        postHttpRequest(DIALOG_OPEN_URL, formParamMap);
    }

    public void postAnswer(SlackDialogSubmissionBean dialogSubmission, String threadTs) throws IOException {
        System.out.println("postAnswer");
        Map<String, String> formParamMap = new HashMap<>();
        formParamMap.put("token", TOKEN);
        formParamMap.put("channel", CHANNEL_ID);
        formParamMap.put("text", dialogSubmission.getAnswer());
        formParamMap.put("username", dialogSubmission.getUserName()); //TODO 指定がない場合は USER_NAME
        formParamMap.put("icon_emoji", dialogSubmission.getUserIcon());//TODO 指定がない場合は USER_ICON
        formParamMap.put("thread_ts", threadTs);
        postHttpRequest(POST_MESSAGE_URL, formParamMap);
    }

    private Response postHttpRequest(String url, Map<String, String> formParamMap) throws IOException {
        OkHttpClient client = new OkHttpClient();
        final FormBody.Builder formBuilder = new FormBody.Builder();
        formParamMap.forEach(formBuilder::add);

        RequestBody requestBody = formBuilder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = client.newCall(request).execute();
        System.out.println("respocse code:" + response.code());
        System.out.println("respocse code:" + response.body().string());
        return response;
    }

    private SlackDialogBean createDialog(){
        SlackDialogBean slackDialog = new SlackDialogBean();
        slackDialog.setCallbackId("ikaqa_answer_dialog");
        slackDialog.setTitle("回答入力フォーム");
        slackDialog.setSubmitLabel("投稿する");

        List<SlackDialogElementBean> list = new ArrayList<SlackDialogElementBean>();
        SlackDialogElementBean slackDialogElement1 = new SlackDialogElementBean();
        slackDialogElement1.setLabel("回答するメッセージ");
        slackDialogElement1.setName("targetLink");
        slackDialogElement1.setType("text");
        slackDialogElement1.setOptional(true);
        slackDialogElement1.setHint("回答するメッセージのリンクは「その他|More Action」メニューから「リンクをコピー|Copy link」を選択すると取得できます");
        slackDialogElement1.setSubtype("url");
        slackDialogElement1.setPlaceholder("回答するメッセージのリンクを貼り付けると、そのメッセージへのリプライとして投稿します");
        list.add(slackDialogElement1);

        SlackDialogElementBean slackDialogElement2 = new SlackDialogElementBean();
        slackDialogElement1.setLabel("回答者の表示名");
        slackDialogElement1.setName("userName");
        slackDialogElement1.setType("text");
        slackDialogElement1.setOptional(true);
        slackDialogElement1.setHint("入力しない場合は自動で匿名になります");
        slackDialogElement1.setSubtype(null);
        slackDialogElement1.setPlaceholder("回答者として表示する名前を入力してください");
        list.add(slackDialogElement2);

        SlackDialogElementBean slackDialogElement3 = new SlackDialogElementBean();
        slackDialogElement1.setLabel("回答者のアイコン");
        slackDialogElement1.setName("userIcon");
        slackDialogElement1.setType("text");
        slackDialogElement1.setOptional(true);
        slackDialogElement1.setHint("入力しない場合は自動で匿名アイコンになります");
        slackDialogElement1.setSubtype(null);
        slackDialogElement1.setPlaceholder("回答者のアイコンとして表示する絵文字(:slack: など)を入力してください");
        list.add(slackDialogElement3);

        SlackDialogElementBean slackDialogElement4 = new SlackDialogElementBean();
        slackDialogElement1.setLabel("回答内容");
        slackDialogElement1.setName("answer");
        slackDialogElement1.setType("textarea");
        slackDialogElement1.setOptional(false);
        slackDialogElement1.setHint("Slackの書式設定したテキストや絵文字も有効です(プレビュー表示はされません)");
        slackDialogElement1.setSubtype(null);
        slackDialogElement1.setPlaceholder("回答内容を入力してください");
        list.add(slackDialogElement4);
        slackDialog.setElements(list);
        return slackDialog;
    }
}



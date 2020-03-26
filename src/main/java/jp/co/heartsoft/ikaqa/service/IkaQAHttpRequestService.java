package jp.co.heartsoft.ikaqa.service;

import com.google.gson.Gson;
import jp.co.heartsoft.ikaqa.bean.SlackAnswerPayloadBean;
import jp.co.heartsoft.ikaqa.bean.SlackDialogBean;
import jp.co.heartsoft.ikaqa.bean.SlackDialogElementBean;
import jp.co.heartsoft.ikaqa.bean.SlackAnswerSubmissionBean;
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
        String dialogJsonStr = gson.toJson(createDialog());
        System.out.println("daialog" + dialogJsonStr);
        formParamMap.put("dialog",dialogJsonStr );
        postHttpRequest(DIALOG_OPEN_URL, formParamMap);
    }

    public void postAnswer(SlackAnswerSubmissionBean submission) throws IOException {
        System.out.println("postAnswer");
        Map<String, String> formParamMap = new HashMap<>();
        formParamMap.put("token", TOKEN);
        formParamMap.put("channel", CHANNEL_ID);
        formParamMap.put("text", submission.getAnswer());
        String userName = submission.getUserName();
        System.out.println("userName:" + userName);
        if(userName.isEmpty()){
            userName = USER_NAME;
        }
        formParamMap.put("username", userName);

        String userIcon = submission.getUserIcon();
        System.out.println("userIcon:" + userIcon);
        if(userIcon.isEmpty()){
            userIcon = USER_ICON;
        }
        formParamMap.put("icon_emoji", userIcon);

        String threadTs = null;
        String targetLink = submission.getTargetLink();
        String ts = targetLink.substring(targetLink.length() - 16);
        if(ts != null){
            threadTs = ts.substring(0, 10) + "." + ts.substring(ts.length() - 6);
        }
        System.out.println("threadTs:" + threadTs);

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
        slackDialogElement2.setLabel("回答者の表示名");
        slackDialogElement2.setName("userName");
        slackDialogElement2.setType("text");
        slackDialogElement2.setOptional(true);
        slackDialogElement2.setHint("入力しない場合は自動で匿名になります");
        slackDialogElement2.setSubtype(null);
        slackDialogElement2.setPlaceholder("回答者として表示する名前を入力してください");
        list.add(slackDialogElement2);

        SlackDialogElementBean slackDialogElement3 = new SlackDialogElementBean();
        slackDialogElement3.setLabel("回答者のアイコン");
        slackDialogElement3.setName("userIcon");
        slackDialogElement3.setType("text");
        slackDialogElement3.setOptional(true);
        slackDialogElement3.setHint("入力しない場合は自動で匿名アイコンになります");
        slackDialogElement3.setSubtype(null);
        slackDialogElement3.setPlaceholder("回答者のアイコンとして表示する絵文字(:slack: など)を入力してください");
        list.add(slackDialogElement3);

        SlackDialogElementBean slackDialogElement4 = new SlackDialogElementBean();
        slackDialogElement4.setLabel("回答内容");
        slackDialogElement4.setName("answer");
        slackDialogElement4.setType("textarea");
        slackDialogElement4.setOptional(false);
        slackDialogElement4.setHint("Slackの書式設定したテキストや絵文字も有効です(プレビュー表示はされません)");
        slackDialogElement4.setSubtype(null);
        slackDialogElement4.setPlaceholder("回答内容を入力してください");
        list.add(slackDialogElement4);
        slackDialog.setElements(list);
        return slackDialog;
    }
}



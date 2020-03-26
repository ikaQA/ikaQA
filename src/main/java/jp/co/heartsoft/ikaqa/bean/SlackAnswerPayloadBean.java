package jp.co.heartsoft.ikaqa.bean;

import com.google.gson.annotations.SerializedName;

public class SlackAnswerPayloadBean {
    @SerializedName("type")
    private String type;
    @SerializedName("token")
    private String token;
    @SerializedName("submission")
    private SlackAnswerSubmissionBean submission;
    @SerializedName("callback_id")
    private String callbackId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SlackAnswerSubmissionBean getSubmission() {
        return submission;
    }

    public void setSubmission(SlackAnswerSubmissionBean submission) {
        this.submission = submission;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }
}

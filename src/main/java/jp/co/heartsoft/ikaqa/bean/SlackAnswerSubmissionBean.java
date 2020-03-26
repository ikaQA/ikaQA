package jp.co.heartsoft.ikaqa.bean;

import com.google.gson.annotations.SerializedName;

public class SlackAnswerSubmissionBean {
    @SerializedName("answer")
    private String answer;
    @SerializedName("userName")
    private String userName;
    @SerializedName("userIcon")
    private String userIcon;
    @SerializedName("targetLink")
    private String targetLink;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getTargetLink() {
        return targetLink;
    }

    public void setTargetLink(String targetLink) {
        this.targetLink = targetLink;
    }
}

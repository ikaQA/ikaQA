package jp.co.heartsoft.ikaqa.bean;

import com.google.gson.annotations.SerializedName;
import jp.co.heartsoft.ikaqa.bean.SlackDialogElementBean;

import java.util.List;

public class SlackDialogBean {
    @SerializedName("title")
    private String title;
    @SerializedName("callback_id")
    private String callbackId;
    @SerializedName("elements")
    private List<SlackDialogElementBean> elements = null;
    @SerializedName("submit_label")
    private String submitLabel;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }

    public List<SlackDialogElementBean> getElements() {
        return elements;
    }

    public void setElements(List<SlackDialogElementBean> elements) {
        this.elements = elements;
    }

    public String getSubmitLabel() {
        return submitLabel;
    }

    public void setSubmitLabel(String submitLabel) {
        this.submitLabel = submitLabel;
    }

}

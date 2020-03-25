package jp.co.heartsoft.ikaqa.bean;

import com.google.gson.annotations.SerializedName;

public class SlackDialogElementBean {

    @SerializedName("label")
    private String label;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("optional")
    private boolean optional;

    @SerializedName("hint")
    private String hint;

    @SerializedName("subtype")
    private String subtype;

    @SerializedName("placeholder")
    private String placeholder;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

}

package com.comicul_inc_dev_comima;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultColor {

    @SerializedName("color")
    @Expose
    private Color color;
    @SerializedName("tagName")
    @Expose
    private String tagName;
    @SerializedName("children")
    @Expose
    private Children children;
    @SerializedName("parent")
    @Expose
    private Parent parent;
    @SerializedName("commentout")
    @Expose
    private Boolean commentout;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Boolean getCommentout() {
        return commentout;
    }

    public void setCommentout(Boolean commentout) {
        this.commentout = commentout;
    }

    public class Parent {

    }

    public class Color {

        @SerializedName("value")
        @Expose
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    public class Children {


    }
}

package com.comicul_inc_dev_comima;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wait {
    @SerializedName("time")
    @Expose
    private Time time;
    @SerializedName("tagName")
    @Expose
    private String tagName;
    @SerializedName("children")
    @Expose
    private Children children;
    @SerializedName("parent")
    @Expose
    private Object parent;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public class Time {

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

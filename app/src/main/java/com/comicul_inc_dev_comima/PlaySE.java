package com.comicul_inc_dev_comima;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaySE {
    @SerializedName("audio")
    @Expose
    private Audio audio;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("volume")
    @Expose
    private Volume volume;
    @SerializedName("laterTime")
    @Expose
    private LaterTime laterTime;
    @SerializedName("loop")
    @Expose
    private Loop loop;
    @SerializedName("tagName")
    @Expose
    private String tagName;
    @SerializedName("children")
    @Expose
    private Children children;
    @SerializedName("parent")
    @Expose
    private String parent;

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public LaterTime getLaterTime() {
        return laterTime;
    }

    public void setLaterTime(LaterTime laterTime) {
        this.laterTime = laterTime;
    }

    public Loop getLoop() {
        return loop;
    }

    public void setLoop(Loop loop) {
        this.loop = loop;
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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public class Volume {

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

    public class Name {

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

    public class Loop {

        @SerializedName("value")
        @Expose
        private Boolean value;

        public Boolean getValue() {
            return value;
        }

        public void setValue(Boolean value) {
            this.value = value;
        }

    }

    public class LaterTime {

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

    public class Audio {

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
}

package com.comicul_inc_dev_comima;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharShow {

    @SerializedName("label")
    @Expose
    private Label label;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("center")
    @Expose
    private Center center;
    @SerializedName("posX")
    @Expose
    private PosX posX;
    @SerializedName("posY")
    @Expose
    private PosY posY;
    @SerializedName("anchorX")
    @Expose
    private AnchorX anchorX;
    @SerializedName("anchorY")
    @Expose
    private AnchorY anchorY;
    @SerializedName("scale")
    @Expose
    private Scale scale;
    @SerializedName("opacity")
    @Expose
    private Opacity opacity;
    @SerializedName("rotate")
    @Expose
    private Rotate rotate;
    @SerializedName("blur")
    @Expose
    private Blur blur;
    @SerializedName("time")
    @Expose
    private Time time;
    @SerializedName("wait")
    @Expose
    private Wait wait;
    @SerializedName("tagName")
    @Expose
    private String tagName;
    @SerializedName("children")
    @Expose
    private Children children;
    @SerializedName("commentout")
    @Expose
    private Boolean commentout;
    @SerializedName("parent")
    @Expose
    private Object parent;

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public PosX getPosX() {
        return posX;
    }

    public void setPosX(PosX posX) {
        this.posX = posX;
    }

    public PosY getPosY() {
        return posY;
    }

    public void setPosY(PosY posY) {
        this.posY = posY;
    }

    public AnchorX getAnchorX() {
        return anchorX;
    }

    public void setAnchorX(AnchorX anchorX) {
        this.anchorX = anchorX;
    }

    public AnchorY getAnchorY() {
        return anchorY;
    }

    public void setAnchorY(AnchorY anchorY) {
        this.anchorY = anchorY;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public Opacity getOpacity() {
        return opacity;
    }

    public void setOpacity(Opacity opacity) {
        this.opacity = opacity;
    }

    public Rotate getRotate() {
        return rotate;
    }

    public void setRotate(Rotate rotate) {
        this.rotate = rotate;
    }

    public Blur getBlur() {
        return blur;
    }

    public void setBlur(Blur blur) {
        this.blur = blur;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Wait getWait() {
        return wait;
    }

    public void setWait(Wait wait) {
        this.wait = wait;
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

    public Boolean getCommentout() {
        return commentout;
    }

    public void setCommentout(Boolean commentout) {
        this.commentout = commentout;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public class Wait {

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

    public class Scale {

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

    public class Rotate {

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

    public class PosY {

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

    public class PosX {

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

    public class Opacity {

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

    public class Label {

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

    public class Image {

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

    public class Center {

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

    public class Blur {

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

    public class AnchorY {

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

    public class AnchorX {

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

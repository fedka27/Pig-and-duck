package app.meat.model.data.repository;

import android.support.annotation.StringRes;

public class Category {
    public final static String TOPIC_ALL = "ALL_CATEGORIES";
    public final static String TOPIC_BIRD = "BIRD";
    public final static String TOPIC_BEEF = "BEEF";
    public final static String TOPIC_PORK = "PORK";
    String topic;
    @StringRes
    int title;
    boolean isCheck ;

    public Category(String topic, @StringRes int title, boolean isCheck) {
        this.topic = topic;
        this.title = title;
        this.isCheck = isCheck;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTitle(@StringRes int title) {
        this.title = title;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getTopic() {
        return topic;
    }

    @StringRes
    public int getTitle() {
        return title;
    }

    public boolean isCheck() {
        return isCheck;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Category && ((Category) obj).getTopic().equals(topic) &&
                ((Category) obj).getTitle() == title;
    }
}

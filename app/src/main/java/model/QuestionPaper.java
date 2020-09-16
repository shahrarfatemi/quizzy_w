package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionPaper {
    @SerializedName("title")
    String title;
    @SerializedName("duration")
    double duration;
    @SerializedName("_id")
    String _id;
    @SerializedName("startTime")
    String startTime;

    @SerializedName("questions")
    List<Question> questions;

    public QuestionPaper(String title, double duration, String _id, String startTime, List<Question> questions) {
        this.title = title;
        this.duration = duration;
        this._id = _id;
        this.startTime = startTime;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}

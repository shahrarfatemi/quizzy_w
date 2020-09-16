package model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Quiz {

    @SerializedName("title")
    String title;
    @SerializedName("password")
    String password;
    @SerializedName("duration")
    double duration;
    @SerializedName("startTime")
    long startTime;

    @SerializedName("tags")
    List<String> tags;
    @SerializedName("questions")
    List<Question> questions;
    @SerializedName("responses")
    List<Response> responses;

    public Quiz(String title, String password, double duration, long startTime,
                List<String> tags, List<Question> questions, List<Response> responses) {
        this.title = title;
        this.password = password;
        this.duration = duration;
        this.startTime = startTime;
        this.tags = tags;
        this.questions = questions;
        this.responses = responses;
    }

    public Quiz() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    @NonNull
    @Override
    public String toString() {
        String str = title + "\n";
        for(int i = 0 ; i < tags.size() ; i++){
            str = str + tags.get(i) + " ";
        }
        str = str + "\n";
        for(int i = 0 ; i < questions.size() ; i++){
            str = str + questions.get(i).getDescription() + "\n";
        }
        return str;
    }
}

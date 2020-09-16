package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnswerResponse {
    @SerializedName("submissions")
    List<Submission> submissions;

    @SerializedName("incorrect")
    List<String> incorrect;

    @SerializedName("correct")
    List<String> correct;

    @SerializedName("_id")
    String _id;

    @SerializedName("quizId")
    String quizId;

    @SerializedName("userId")
    String userId;

    @SerializedName("marks")
    Double marks;

    public AnswerResponse(List<Submission> submissions, List<String> incorrect, List<String> correct,
                          String _id, String quizId, String userId, Double marks) {
        this.submissions = submissions;
        this.incorrect = incorrect;
        this.correct = correct;
        this._id = _id;
        this.quizId = quizId;
        this.userId = userId;
        this.marks = marks;
    }

    public AnswerResponse() {
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public List<String> getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(List<String> incorrect) {
        this.incorrect = incorrect;
    }

    public List<String> getCorrect() {
        return correct;
    }

    public void setCorrect(List<String> correct) {
        this.correct = correct;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }
}

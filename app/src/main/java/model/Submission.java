package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Submission {

    @SerializedName("questionId")
    String questionId;

    @SerializedName("answers")
    List<String> answers;

    public Submission(String questionId, List<String> answers) {
        this.questionId = questionId;
        this.answers = answers;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}


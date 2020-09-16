package model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AnswerReview extends AnswerResponse {

    @SerializedName("createdAt")
    String createdAt;
    @SerializedName("updatedAt")
    String updatedAt;
    @SerializedName("rating")
    Double rating;

    public AnswerReview(List<Submission> submissions, List<String> incorrect, List<String> correct, String _id,
                        String quizId, String userId, Double marks, String createdAt, String updatedAt, Double rating) {
        super(submissions, incorrect, correct, _id, quizId, userId, marks);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.rating = rating;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getCreateDate() throws Exception{
        Date createDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(createdAt);
        return createDate;
    }

    public Date getUpdateDate() throws Exception{
        Date updateDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(updatedAt);
        return updateDate;
    }

}

package model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizFeed extends QuizResponse {
    @SerializedName("imageUri")
    Uri imageUri;
    @SerializedName("access")
    String access;
    @SerializedName("rating")
    Double rating;
    @SerializedName("difficulty")
    Double difficulty;
    @SerializedName("ownerName")
    String ownerName;
    @SerializedName("userCount")
    int userCount;
    @SerializedName("questionCount")
    int questionCount;
    public QuizFeed(String title, String owner, double duration, String _id, String startTime,
                    List<String> tags, String access, Double rating, Double difficulty,
                    String ownerName, int questionCount, int userCount) {
        super(title, owner, duration, _id, startTime, tags);
        this.access = access;
        this.rating = rating;
        this.difficulty = difficulty;
        this.ownerName = ownerName;
        this.questionCount = questionCount;
        this.userCount = userCount;
    }


    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }
}

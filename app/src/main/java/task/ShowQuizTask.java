package task;

import java.util.List;

import model.QuizResponse;

public interface ShowQuizTask {
    void showMyQuizzes(List<QuizResponse> quizzes);
    void onFailure(String msg);
}

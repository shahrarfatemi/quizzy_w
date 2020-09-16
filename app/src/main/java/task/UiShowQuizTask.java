package task;

import java.util.List;

import model.QuizResponse;

public interface UiShowQuizTask {
    void showMyQuizzes(List<QuizResponse> quizzes);
}

package task;

import model.QuizResponse;

public interface CreateQuizTask {
    void createQuiz(QuizResponse quizResponse);
    void onFailure(String msg);
}

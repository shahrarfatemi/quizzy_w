package task;

import model.QuizResponse;

public interface EditQuizTask {
    void editQuiz(QuizResponse quizResponse);
    void onFailure(String msg);
}

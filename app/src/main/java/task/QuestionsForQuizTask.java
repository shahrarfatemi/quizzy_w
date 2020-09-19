package task;

import model.QuestionPaper;

public interface QuestionsForQuizTask {
    void getQuestionsForQuiz(QuestionPaper questionPaper);
    void onFailure(String msg);
}

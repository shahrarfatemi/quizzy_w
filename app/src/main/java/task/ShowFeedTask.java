package task;

import java.util.List;

import model.QuizFeed;

public interface ShowFeedTask {
    void showTopFeedQuizzes(List<QuizFeed> quizzes);
}

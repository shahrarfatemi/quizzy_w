package task;

import java.util.List;

import model.QuizFeed;

public interface UiShowFeedTask {
    void showTopFeedQuizzes(List<QuizFeed> quizzes);
}

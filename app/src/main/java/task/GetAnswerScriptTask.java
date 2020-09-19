package task;

import model.AnswerResponse;

public interface GetAnswerScriptTask {
    void getAnswerScript(AnswerResponse answerResponse);
    void onFailure(String msg);
}

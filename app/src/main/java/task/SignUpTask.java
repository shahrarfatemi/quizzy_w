package task;

import model.UserResponse;

public interface SignUpTask {
    void signUp(UserResponse userResponse);
    void onFailure(String msg);
}

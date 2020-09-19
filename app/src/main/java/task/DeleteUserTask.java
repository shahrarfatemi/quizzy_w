package task;

import model.UserInfo;

public interface DeleteUserTask {
    void deleteUser(UserInfo userInfo);
    void onFailure(String msg);
}

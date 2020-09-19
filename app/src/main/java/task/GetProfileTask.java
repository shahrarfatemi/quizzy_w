package task;

import model.UserInfo;

public interface GetProfileTask {
    void getMyProfile(UserInfo userInfo);
    void onFailure(String msg);
}

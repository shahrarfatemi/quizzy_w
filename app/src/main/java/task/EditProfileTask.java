package task;

import model.UserInfo;

public interface EditProfileTask {
    void editProfile(UserInfo userInfo);
    void onFailure(String msg);
}

package task;

import android.graphics.Bitmap;

public interface FetchProfilePictureTask {
    void fetchProfilePicture(Bitmap bitmap);
    void onFailure(String msg);
}

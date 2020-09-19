package com.example.quizzy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import image.ImageUtil;
import model.QuizFeed;
import model.QuizResponse;
import model.UserResponse;
import network.NetworkUtilAccount;
import network.NetworkUtilQuiz;
import okhttp3.MultipartBody;
import task.FetchProfilePictureTask;
import task.ImageLoad;
import task.ShowFeedTask;
import task.ShowQuizTask;
import task.UpdateProfilePictureTask;

public class HomeActivity extends AppCompatActivity {

    TextView showText, showFeedText;
    Button showQuizButton, showFeedButton, chooseImageButton, postImageButton, showImageButton;

    NetworkUtilQuiz networkUtilQuiz;
    NetworkUtilAccount networkUtilAccount;
    String token;
    byte[] bytes;

    private static final int IMAGE_REQUEST = 1;
    private static final int IMAGE_PERMISSION = 1;

    Uri imageUri;
    ImageView imageView;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init(){
        Intent intent = getIntent();
        UserResponse userResponse = (UserResponse) intent.getSerializableExtra("MyInfo");
        token = userResponse.getToken();
        userName = userResponse.getUserInfo().getName();
        showText = (TextView) findViewById(R.id.showText);
        showFeedText = (TextView) findViewById(R.id.showFeedText);
        showQuizButton = (Button) findViewById(R.id.showButton);
        showFeedButton = (Button) findViewById(R.id.showFeedButton);
        chooseImageButton = (Button) findViewById(R.id.chooseImageButton);
        postImageButton = (Button) findViewById(R.id.postImageButton);
        showImageButton = (Button) findViewById(R.id.showImageButton);

        imageView = (ImageView) findViewById(R.id.imageId);

        networkUtilQuiz =  new NetworkUtilQuiz();
        networkUtilAccount = new NetworkUtilAccount();

        showQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkUtilQuiz.showMyQuiz(token, new ShowQuizTask() {
                    @Override
                    public void showMyQuizzes(List<QuizResponse> quizzes) {
                        Log.d("response in feed  ","got quizzes");
                        showText.setText(quizzes.get(0).getOwner());
                    }

                    @Override
                    public void onFailure(String msg) {
                        Log.d("failed login ",msg);
                    }
                });
                Log.d("response in feed  ","pressed show button");
            }
        });

        showFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("tag","General");
                hashMap.put("title","Engineering");
                networkUtilQuiz.showTopFeedQuizzes(token, hashMap, 0, 2, new ShowFeedTask() {
                    @Override
                    public void showTopFeedQuizzes(List<QuizFeed> quizzes) {
                        Log.d("response in feed  ","got quizzes ");
                        showFeedText.setText(quizzes.get(0).getTitle());
                    }
                    @Override
                    public void onFailure(String msg) {
                        Log.d("failed login ",msg);
                    }
                });
                Log.d("response in feed  ","pressed show feed button");
            }
        });

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(HomeActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    chooseImage();
                }
                else{
                    requestPermission();
                }
                Log.d("choosing image =>","here to choose image");
            }
        });

        postImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("posting image =>","here to post image");
                postImage();

            }
        });

        showImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                networkUtilAccount.getProfilePicture("5f5f6180daba410017874667", new FetchProfilePictureTask() {
//                    @Override
//                    public void fetchProfilePicture(Bitmap bitmap) {
//                        imageView.setImageBitmap(bitmap);
//                        Log.d("fecth pic => ","done");
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        Log.d("fecth pic => ","failed");
//                    }
//                });
                ImageLoad imageLoad = new ImageLoad("https://contest-quiz-app.herokuapp.com/users/5f5f6180daba410017874667/avatar",
                        imageView);
                imageLoad.execute();
            }
        });
    }

    private void postImage(){
        if(imageUri == null){
            return;
        }
        else{
            try {
                //convert to binary
                bytes = ImageUtil.convertToBytes(this, imageUri);

                File imageFile = ImageUtil.convertToFile(this, imageUri, "avatar."+ImageUtil.getExtensionFromUri(
                        this, imageUri
                ));
//                for (int i = 0; i < bytes.length; i++) {
//                    Log.d("bytes converted", "byte" + i + "=>" + bytes[i]);
//                }
                Log.d("imagefile", "total space "+imageFile.getTotalSpace());
                MultipartBody.Part body = ImageUtil.toMultiPartFile("avatar",imageFile);
//                RequestBody body = RequestBody.create(MediaType.parse(getContentResolver().getType(imageUri)), imageFile);;
//                Log.d("bytes converted",  body.toString());
//                Map<String, RequestBody> map = new HashMap<>();
//                map.put("avatar", body);
//                MultipartBody.Builder builder = new MultipartBody.Builder();
//                builder.setType(MultipartBody.FORM);
//                builder.addFormDataPart("avatar", imageFile.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), imageFile));
//                MultipartBody requestBody = builder.build();
                networkUtilAccount.updateProfilePicture(token, body, new UpdateProfilePictureTask() {
                    @Override
                    public void updateProfilePicture() {
                        Log.d("update =>","updated profile picture successfully");
                    }

                    @Override
                    public void onFailure(String msg) {
                        Log.d("failed login ",msg);
                    }
                });
            }catch (Exception e){
                Log.d("Exception =>", e.getMessage());
            }
        }
    }

    public void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this).
                    setTitle("Permission needed").
                    setMessage("bla bla bla").
                    setPositiveButton("okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(HomeActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                                    IMAGE_PERMISSION);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        }
        else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == IMAGE_PERMISSION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(HomeActivity.this, "Granted", Toast.LENGTH_LONG).show();
                chooseImage();
            }
            else {
                Toast.makeText(HomeActivity.this, "Not granted", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            try {
                imageUri = data.getData();

//            Picasso.with(this).load(imageUri).into(imageView);
                Picasso.get().load(imageUri).into(imageView);


            }catch (Exception e){
                Log.d("Exception",e.getMessage());
            }

        }
    }
}
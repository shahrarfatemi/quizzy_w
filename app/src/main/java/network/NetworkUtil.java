package network;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Quiz;
import model.QuizResponse;
import model.User;
import model.UserInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import task.UiCreateQuizTask;
import task.UiDeleteUserTask;
import task.UiEditProfileTask;
import task.UiGetProfileTask;
import task.UiLogOutTask;
import task.UiLoginTask;
import task.UiShowQuizTask;
import task.UiSignUpTask;

public class NetworkUtil {
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;
    String BaseUrl = "https://contest-quiz-app.herokuapp.com/";

//    private static NetworkUtil networkUtil;

    public NetworkUtil(){
        retrofit = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

//    public static NetworkUtil getInstance(){
//        if(networkUtil == null){
//            networkUtil = new NetworkUtil();
//        }
//        return networkUtil;
//    }

    public void handleSignup(String name, String email, String pass, final @Nullable UiSignUpTask callBack){
        HashMap<String,String> hashMap = new HashMap<>();

        hashMap.put("name",name);
        hashMap.put("email",email);
        hashMap.put("password",pass);

        Call<UserInfo> call = retrofitInterface.executeSignup(hashMap);

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                //Toast.makeText(MainActivity.this,response.code()., Toast.LENGTH_LONG).show();
                if(response.code() == 201){
                    try {
                        UserInfo userInfo = (UserInfo) response.body();
                        Log.d("email ",userInfo.getUser().getEmail());
                        callBack.signUp(userInfo);
//                        Toast.makeText(SignupActivity.this, "saved info successfully " + name +"\n"+email+ "\n"+token, Toast.LENGTH_LONG).show();
//                        saveInfo(name, email, token);
//                        Intent intent = new Intent(SignupActivity.this,HomeActivity.class);
//                        intent.putExtra("MyInfo",userInfo);
//                        startActivity(intent);
//                        finish();
                    }catch (Exception e){
                        Log.d("exception ",e.getMessage());
                    }
                }else if(response.code() == 400){
                    Log.d("response ","code => "+response.code());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.d("failure ",t.toString());
            }
        });

    }



    public void handleLogin(String email, final String pass, final @Nullable UiLoginTask callBack){
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("email",email);
        hashMap.put("password",pass);

        Call<UserInfo> call = retrofitInterface.executeLogin(hashMap);

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                //Toast.makeText(MainActivity.this,response.code()., Toast.LENGTH_LONG).show();
                if(response.code() == 200){
                    try {
                        UserInfo userInfo = (UserInfo) response.body();
                        callBack.logIn(userInfo);
                    }catch (Exception e){
                        Log.d("exception ",e.getMessage());
                    }
                }else if(response.code() == 400){
                    Log.d("response ","code => "+response.code());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.d("failure ",t.toString());
            }
        });

    }


    public void logOut(String header, @Nullable final UiLogOutTask callBack){
        Call<Void> call = retrofitInterface.executeLogout("Bearer "+header);
//        Toast.makeText(HomeActivity.this,"inside this method",Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //Toast.makeText(MainActivity.this,response.code()., Toast.LENGTH_LONG).show();
                if(response.code() == 200){
                    try {
                        callBack.logOut();
//                        Toast.makeText(HomeActivity.this, "logged out successfully ", Toast.LENGTH_LONG).show();
//                        removeInfo();
//                        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
//                        startActivity(intent);
//                        finish();
                    }catch (Exception e){
                        Log.d("exception ",e.getMessage());
                    }
                }else if(response.code() == 400){
                    Log.d("response ","code => "+response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("failure ",t.toString());
            }
        });

    }

    public void logoutAllDevice(String header, @Nullable final UiLogOutTask callBack){
        //format of the header is : header-name "Authorization", header-value "Bearer <token>"
        Call<Void> call = retrofitInterface.executeLogoutAll("Bearer "+header);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //Toast.makeText(MainActivity.this,response.code()., Toast.LENGTH_LONG).show();
                if(response.code() == 200){
                    try {
//                        Toast.makeText(HomeActivity.this, "logged out from all devices successfully ", Toast.LENGTH_LONG).show();
                        callBack.logOut();
                    }catch (Exception e){
                        Log.d("exception ",e.getMessage());
                    }
                }else if(response.code() == 500){
                    Log.d("response ","code => "+response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("failure ",t.toString());
            }
        });

    }
    


}

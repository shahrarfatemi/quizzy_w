package network;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.HashMap;

import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import task.UiDeleteProfilePictureTask;
import task.UiDeleteUserTask;
import task.UiEditProfileTask;
import task.UiGetProfileTask;

public class NetworkUtilAccount extends NetworkUtil {

    public NetworkUtilAccount() {
        super();
    }

    public void getProfile(String header, final @Nullable UiGetProfileTask callBack){
        Log.d("before request ",header);
        Call<User> call = retrofitInterface.executeGetMyProfile("Bearer "+header);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //Toast.makeText(FirstActivity.this,response.code()., Toast.LENGTH_LONG).show();
                if(response.code() == 200){
                    try {
                        Log.d("before getting profile ","code => "+response.code());
                        User user = (User) response.body();
                        Log.d("got profile ",user.getEmail());
                        callBack.getMyProfile(user);
                    }catch (Exception e){
                        Log.d("exception ",e.getMessage());
                    }
                }else if(response.code() == 400){
                    Log.d("response ","code => "+response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("failure ",t.toString());
            }
        });
    }

    public void deleteMyAccount(String header, @Nullable final UiDeleteUserTask callBack){
        Call<User> call = retrofitInterface.executeDeleteMyAccount("Bearer "+header);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200){
                    try {
//                        Toast.makeText(HomeActivity.this, "deleted successfully ", Toast.LENGTH_LONG).show();
                        Log.d("call => ","before jsonUser");
                        User user = (User) response.body();
                        Log.d("call => ","object Got");
                        callBack.deleteUser(user);
                    }catch (Exception e){
                        Log.d("exception ",e.getMessage());
                    }
                }else if(response.code() == 500){
                    Log.d("response ","code => "+response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("failure ",t.toString());
            }
        });
    }

    public void editMyAccount(String header, String name, String password, @Nullable final UiEditProfileTask callBack){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("password",password);
        Call<User> call = retrofitInterface.executeEditMyAccount("Bearer "+header, hashMap);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //Toast.makeText(MainActivity.this,response.code()., Toast.LENGTH_LONG).show();
                if(response.code() == 200){
                    try {
                        //the server sends back the name,email,_id etc of the updated user,actually it can respond with other info too,
                        // check the doc for that we can get any string in the same way as below so pera naai
                        // new email and name has been assigned to the user and then updated in the sqlite database
                        Log.d("call => ","before jsonUser");
                        User user = ((User) response.body());
                        Log.d("call => ","object Got");
                        callBack.editProfile(user);

                    }catch (Exception e){
                        Log.d("exception ",e.getMessage());
                    }
                }else if(response.code() == 400){
//                    Toast.makeText(HomeActivity.this, "could not edit", Toast.LENGTH_LONG).show();
                    Log.d("response ","code => "+response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("failure ",t.toString());
            }
        });
    }

    public void deleteProfilePicture(String header, @Nullable final UiDeleteProfilePictureTask callBack){
        Call<Void> call = retrofitInterface.executeDeleteProfilePicture("Bearer "+header);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200){
                    try {
//                        Toast.makeText(HomeActivity.this, "deleted successfully ", Toast.LENGTH_LONG).show();
                        Log.d("call => ","after response");
                        callBack.deleteProfilePicture();
                    }catch (Exception e){
                        Log.d("exception ",e.getMessage());
                    }
                }else {
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

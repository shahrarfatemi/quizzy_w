package network;

import java.util.HashMap;
import java.util.List;

import model.AnswerResponse;
import model.AnswerReview;
import model.QuestionPaper;
import model.Quiz;
import model.QuizFeed;
import model.QuizResponse;
import model.Submission;
import model.User;
import model.UserInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @POST("/users")
    Call<UserInfo> executeSignup (@Body HashMap<String,String> hashMap);

    @POST("/users/login")
    Call<UserInfo> executeLogin (@Body HashMap<String,String> hashMap);

    @GET("/users/me")
    Call<User> executeGetMyProfile (@Header("Authorization") String header);

    @POST("/users/logout")
    Call<Void> executeLogout (@Header("Authorization") String header);

    @POST("/users/logoutAll")
    Call<Void> executeLogoutAll (@Header("Authorization") String header);

    @DELETE("/users/me")
    Call<User> executeDeleteMyAccount (@Header("Authorization") String header);

    @PATCH("/users/me")
    Call<User> executeEditMyAccount (@Header("Authorization") String header, @Body HashMap<String,String> hashMap);

    @POST("/quizzes")
    Call<QuizResponse> executeCreateQuiz (@Header("Authorization") String header, @Body Quiz quiz);

    @GET("/quizzes/me")
    Call<List<QuizResponse>> executeGetMyQuiz(@Header("Authorization") String header);

    @DELETE("/quizzes/{id}")
    Call<Void> executeDeleteQuiz(@Header("Authorization") String header, @Path("id") String id);

    @PATCH("/quizzes/{id}")
    Call<QuizResponse> executeEditQuiz(@Header("Authorization") String header, @Path("id") String id, @Body Quiz quiz);

    @GET("/quizzes")
    Call<List<QuizFeed>> executeRecentQuiz(@Header("Authorization") String header, @Query("title") String title, @Query("tag") String tag,
                                     @Query("skip") int skip, @Query("limit") int limit);

    @GET("/quizzes")
    Call<List<QuizFeed>> executeRecentQuizByTag(@Header("Authorization") String header, @Query("tag") String tag,
                                        @Query("skip") int skip,@Query("limit") int limit);

    @GET("/quizzes")
    Call<List<QuizFeed>> executeRecentQuizByTitle(@Header("Authorization") String header, @Query("title") String title,
                                          @Query("skip") int skip,@Query("limit") int limit);

    @GET("/quizzes")
    Call<List<QuizFeed>> executeRecentQuizSimple(@Header("Authorization") String header,
                                         @Query("skip") int skip,@Query("limit") int limit);

    @GET("/quizzes/{id}")
    Call<QuestionPaper> executeQuizQuestionPrivate(@Header("Authorization") String header, @Path("id") String id,
                                                   @Query("pwd") String password);

    @GET("/quizzes/{id}")
    Call<QuestionPaper> executeQuizQuestionPublic(@Header("Authorization") String header, @Path("id") String id);

    @POST("/quizzes/{id}")
    Call<AnswerResponse> executeSubmitAnswer(@Header("Authorization") String header, @Path("id") String id,
                                                 @Body HashMap<String, List<Submission>> hashMap);

    @POST("/quizzes/review/:id")
    Call<AnswerReview> executePostRating(@Header("Authorization") String header, @Path("id") String id,
                                           @Body HashMap<String, Double> hashMap);

//some are yet to add


    @DELETE("/users/me/avatar")
    Call<Void> executeDeleteProfilePicture(@Header("Authorization") String header);

}

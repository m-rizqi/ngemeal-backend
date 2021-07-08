package Main;

import java.util.List;

import Model.Post;
import Repository.Retrofit.JsonPlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMain {
    public static void main(String[] args) {
        Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<Post>>(){
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Error code : "+response.code());
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    System.out.println("ID : "+post.getId());
                    System.out.println("User ID : "+post.getUserId());
                    System.out.println("Title : "+post.getTitle());
                    System.out.println("Text : "+post.getText());
                    System.out.println("\n");
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                System.out.println("Failure : "+t.getMessage());
            }
        });
    }
}

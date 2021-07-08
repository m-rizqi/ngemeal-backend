package Repository.Retrofit;

import retrofit2.http.GET;
import retrofit2.Call;
import java.util.*;

import Model.Post;
public interface JsonPlaceHolderApi {
    
    @GET("posts")
    Call<List<Post>> getPost();
}

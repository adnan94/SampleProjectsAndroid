package fizyo.revolutionary.com.retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Pojoclass>> getHeroes();

    @GET("android/downloadcode/objectfile.json")
    Call<model> getModel();
}

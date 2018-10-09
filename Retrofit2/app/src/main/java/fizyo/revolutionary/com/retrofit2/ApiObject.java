package fizyo.revolutionary.com.retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiObject {
//    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("android/downloadcode/objectfile.json")
    Call<model> getModel();
}

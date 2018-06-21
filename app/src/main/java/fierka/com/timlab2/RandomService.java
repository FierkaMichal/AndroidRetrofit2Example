package fierka.com.timlab2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Michał on 2018-06-21.
 */

public interface RandomService {

    String API_BASE_URL = "http://192.168.1.10:8082";

//    @GET("getRandom?numberCount={numberCount}")
//    Call<List<Integer>> getRandom(@Path("getRandom") Integer numberCount);

    @GET("/getRandom")
    Call<List<Integer>> getRandom(@Query("numbersCount") Integer numbersCount);
}

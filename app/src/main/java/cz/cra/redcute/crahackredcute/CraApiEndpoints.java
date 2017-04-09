package cz.cra.redcute.crahackredcute;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by obrusvit on 7.4.17.
 */

public interface CraApiEndpoints {
    String BASE_URL = "https://api.pripoj.me/";
    String TOKEN = "xo9aPO0YBDZdfESRJwj0imwtRUzBIvmA";

    @GET("/message/get/{dev_eui}")
    Call<String> getResponse(@Path("dev_eui") String devEui,
                                 @Query("token") String token);



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();
}

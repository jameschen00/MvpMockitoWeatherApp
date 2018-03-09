package util;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by vrushali on 3/9/18.
 */

public class ErrorResponseGenerator {

    public static Throwable generateWithStatusMessageAndStatusCode(String value, int statusCode) {
        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();
        return new HttpException(
                Response.error(
                        ResponseBody.create(MediaType.parse("application/json"),
                                String.format("{\"statusMessage\":\"%s\"}", value)),
                        new okhttp3.Response.Builder()
                                .request(request).protocol(Protocol.HTTP_2)
                                .code(statusCode)
                                .addHeader("Error-Message", value).build()
                )
        );
    }
}

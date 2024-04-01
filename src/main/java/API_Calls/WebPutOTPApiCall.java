package API_Calls;

import com.google.gson.Gson;
import model.PutOTP;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static Helper.BaseClass.client;

public class WebPutOTPApiCall {
    static String apiUrl = "https://dawak-apim-uat.azure-api.net/dawak-auth/api/auth/verifyOtp";
    static String accessToken;

    public static String OTPApiCall(String jsonFile) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            WebPutOTPApiCall putOTPApiCall = new WebPutOTPApiCall();
            String jsonPayload = gson.toJson(putOTPApiCall.getPutOtp(jsonFile));
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(RequestBody.create(jsonPayload, MediaType.parse("application/json")))
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                JSONObject data = jsonResponse.getJSONObject("data");
                JSONObject token = data.getJSONObject("token");
                accessToken = String.valueOf(token.getString("accessToken"));
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PutOTP getPutOtp(String fileName) {
        try (Reader reader = new InputStreamReader(this.getClass()
                .getResourceAsStream("/"+fileName+".json"))) {
            PutOTP result = new Gson().fromJson(reader, PutOTP.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

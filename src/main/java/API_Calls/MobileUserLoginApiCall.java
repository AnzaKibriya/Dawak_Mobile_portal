package API_Calls;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.LoginMobile;
import model.LoginWeb;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static Helper.BaseClass.client;
import static Helper.BaseClass.test;

public class MobileUserLoginApiCall {
    static String apiUrl = "https://dawak-apim-uat.azure-api.net/dawak-auth/api/auth/v3/mobile-login";
    static String mobileUserAccessToken;

    public static String makeMobileLoginApiCall(String jsonFile) {
        try{
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            MobileUserLoginApiCall loginCpApiCall = new MobileUserLoginApiCall();
            String jsonPayload = gson.toJson(loginCpApiCall.getMobileLogin(jsonFile));
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(RequestBody.create(jsonPayload, MediaType.parse("application/json")))
                    .addHeader("Content-Type", "application/json")
                    .addHeader("deviceType", "ios")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                test.log(Status.PASS, "Login API called successfully");
                JSONObject jsonResponse = new JSONObject(response.body().string());
                JSONObject data = jsonResponse.getJSONObject("data");
                mobileUserAccessToken = String.valueOf(data.getString("accessToken"));
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
            return mobileUserAccessToken;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return mobileUserAccessToken;
    }
    public LoginMobile getMobileLogin(String fileName) {
        try (Reader reader = new InputStreamReader(this.getClass()
                .getResourceAsStream("/"+fileName+".json"))) {
            LoginMobile result = new Gson().fromJson(reader, LoginMobile.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

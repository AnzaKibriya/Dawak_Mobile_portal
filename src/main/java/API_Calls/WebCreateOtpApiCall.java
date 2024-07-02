package API_Calls;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.CreateOtp;
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

public class WebCreateOtpApiCall {
    static String apiUrl = BaseClass.propertyFile("config","url")+"/dawak-auth/api/auth/createOtp";
    public static void createOtpApiCall(String jsonFile) {
        try{
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            WebCreateOtpApiCall createOtpApiCall = new WebCreateOtpApiCall();
            String jsonPayload = gson.toJson(createOtpApiCall.getCreateOtp(jsonFile));
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(RequestBody.create(jsonPayload, MediaType.parse("application/json")))
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                test.log(Status.PASS, "create otp API called successfully");
                JSONObject jsonResponse = new JSONObject(response.body().string());
//                JSONObject data = jsonResponse.getJSONObject("data");
//                accessToken = data.getString("access_token");
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public CreateOtp getCreateOtp(String fileName) {
        try (Reader reader = new InputStreamReader(this.getClass()
                .getResourceAsStream("/"+fileName+".json"))) {
            CreateOtp result = new Gson().fromJson(reader, CreateOtp.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package API_Calls;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.Login;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static Helper.BaseClass.*;
import static Helper.BaseClass.test;

public class LoginApiCall  {
    static String apiUrl  = BaseClass.propertyFile("config","url")+"/dawak-auth/api/auth/purenet/login";
    public static String makeLoginApiCall() {
        try{
            test.log(Status.INFO,"Create New Prescription for a New Patient");
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            LoginApiCall loginApiCall = new LoginApiCall();
            String jsonPayload = gson.toJson(loginApiCall.getLogin());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(RequestBody.create(jsonPayload, MediaType.parse("application/json")))
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                test.log(Status.PASS, "Login to Purenet successfull");
                JSONObject jsonResponse = new JSONObject(response.body().string());
                JSONObject data = jsonResponse.getJSONObject("data");
                accessToken = data.getString("access_token");
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
            return accessToken;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Login getLogin() {
        try (Reader reader = new InputStreamReader(this.getClass()
                .getResourceAsStream("/Login.json"))) {
            Login result = new Gson().fromJson(reader, Login.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

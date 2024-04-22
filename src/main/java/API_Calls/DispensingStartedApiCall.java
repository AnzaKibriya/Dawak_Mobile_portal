package API_Calls;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.DispensingStarted;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static API_Calls.GetDPTaskApiCall.getTaskIdDp;
import static Helper.BaseClass.*;

public class DispensingStartedApiCall {
    static String apiUrl =
            "https://dawak-apim-uat.azure-api.net/dawak-portal/api/dispensing-pharmacist/dispense-inprogress-confirmation";

    public static void getDispensingStartedApiCall(String AUTH_TOKEN) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            DispensingStartedApiCall dispensingStartedApiCall = new DispensingStartedApiCall();
            String jsonPayload = gson.toJson(dispensingStartedApiCall.getDispensingStarted());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                test.log(Status.PASS, "Get Dispensing Started API called successfully");
                JSONObject jsonResponse = new JSONObject(response.body().string());
                System.out.println(jsonResponse);

            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public DispensingStarted getDispensingStarted() {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/DispensingStarted.json"))) {
            Gson gson = new Gson();
            DispensingStarted result = gson.fromJson(reader, DispensingStarted.class);
            result.setId(getTaskIdDp());
            result.setEncounterId(prescriptionOrderID);//prescriptionOrderID
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

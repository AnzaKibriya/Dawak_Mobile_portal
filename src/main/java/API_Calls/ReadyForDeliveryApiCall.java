package API_Calls;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.ReadyForDelivery;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static API_Calls.GetDPTaskApiCall.*;
import static Helper.BaseClass.*;

public class ReadyForDeliveryApiCall {
    static String apiUrl =
            "https://dawak-apim-uat.azure-api.net/dawak-portal/api/dispensing-pharmacist/ready-to-pickup-confirmation";

    public static void getReadyForDeliveryApiCall(String AUTH_TOKEN) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            ReadyForDeliveryApiCall readyForDeliveryApiCall = new ReadyForDeliveryApiCall();
            String jsonPayload = gson.toJson(readyForDeliveryApiCall.getReadyForDelivery());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                test.log(Status.PASS, "Get Ready for Delivery API called successfully");
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


    public ReadyForDelivery getReadyForDelivery() {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/ReadyForDelivery.json"))) {
            Gson gson = new Gson();
            ReadyForDelivery result = gson.fromJson(reader, ReadyForDelivery.class);
            result.setTaskId(String.valueOf(getTaskIdDp()));
            result.setId(Integer.parseInt(getEncounterIDDp()));
            result.setEncounterId(prescriptionOrderID);//prescriptionOrderID
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

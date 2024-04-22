package API_Calls;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.SendForInsurance;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static API_Calls.GetCPTaskApiCall.getEncounterID;
import static Helper.BaseClass.*;

public class SendForInsuranceApiCall {
    private static final String API_URL = "https://dawak-apim-uat.azure-api.net/dawak-portal/api/pharmacist/approve-order";
    public static void getSendForInsuranceApiCall(String AUTH_TOKEN) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            SendForInsuranceApiCall sendForInsuranceApiCall = new SendForInsuranceApiCall();
            String jsonPayload = gson.toJson(sendForInsuranceApiCall.getSendForInsurance());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                test.log(Status.PASS, "send for insurence API called successfully");
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


    public SendForInsurance getSendForInsurance() {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/SendForInsurance.json"))) {
            Gson gson = new Gson();
            SendForInsurance result = gson.fromJson(reader, SendForInsurance.class);
            result.setId(Integer.parseInt(getEncounterID()));
            result.setEncounterId(prescriptionOrderID);
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

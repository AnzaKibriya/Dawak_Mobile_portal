package API_Calls;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.MedicationPaymentInfo;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static Helper.BaseClass.client;
import static API_Calls.GetCPTaskApiCall.getEncounterID;
import static Helper.BaseClass.test;

public class MedicationCoPayApiCall {
    private static final String API_URL = "https://dawak-apim-uat.azure-api.net/dawak-portal/api/pharmacist/v2/add-copay";
    public static void getMedicationCoPayApiCall(String AUTH_TOKEN, int medicationRequestID, String jsonFile) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            MedicationCoPayApiCall metforminCoPayApiCall = new MedicationCoPayApiCall();
//            metforminCoPayApiCall.getMedicationPaymentInfo(medicationRequestID);
            String jsonPayload = gson.toJson(metforminCoPayApiCall.getMedicationPaymentInfo(medicationRequestID, jsonFile));
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                test.log(Status.PASS, "Get Medicine API called successfully");
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


    public MedicationPaymentInfo getMedicationPaymentInfo(int medicationRequestID, String fileName) {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/"+fileName+".json"))) {
            Gson gson = new Gson();
            MedicationPaymentInfo result = gson.fromJson(reader, MedicationPaymentInfo.class);
            result.setId(Integer.parseInt(getEncounterID()));
            result.setMedicationRequestId(medicationRequestID);
            result.setPrescriptionNumber(getEncounterID());
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

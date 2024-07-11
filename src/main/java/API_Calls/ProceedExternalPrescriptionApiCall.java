package API_Calls;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.ProceedExternalPrescription;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import static API_Calls.GetCPTaskApiCall.getEncounterID;
import static Helper.BaseClass.client;
import static Helper.BaseClass.test;

public class ProceedExternalPrescriptionApiCall {
    static String apiUrl = BaseClass.propertyFile("config", "url") + "/dawak-portal/api/external-prescription/proceed";

    public static void makeProceedExternalPrescriptionApiCall(String authToken) {
        try {
            test.log(Status.INFO, "Verify Insurance Card of the Patient");
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            ProceedExternalPrescriptionApiCall proceedExternalPrescriptionApiCall = new ProceedExternalPrescriptionApiCall();
            String jsonPayload = gson.toJson(proceedExternalPrescriptionApiCall.getProceedExternalPrescription());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .header("Authorization", "Bearer " + authToken)
                    .addHeader("Content-Type", "application/json")
                    .post(RequestBody.create(jsonPayload, MediaType.parse("application/json")))
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
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

    public ProceedExternalPrescription getProceedExternalPrescription() {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/ProceedExternalPrescription.json"))) {
            Gson gson = new Gson();
            ProceedExternalPrescription result = gson.fromJson(reader, ProceedExternalPrescription.class);
            result.setEncounterId(Integer.parseInt(getEncounterID()));
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

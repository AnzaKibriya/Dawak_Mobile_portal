package API_Calls;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.AddExternalMedication;
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

public class ExternalPrescriptionAddMedicationApiCall {
    static String apiUrl = BaseClass.propertyFile("config", "url") + "/dawak-portal/api/pharmacist/add-medication";

    public static void makeExternalPrescriptionAddMedicationApiCall(String authToken) {
        try {
            test.log(Status.INFO, "Add Medication For External Prescription");
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            ExternalPrescriptionAddMedicationApiCall externalPrescriptionAddMedicationApiCall = new ExternalPrescriptionAddMedicationApiCall();
            String jsonPayload = gson.toJson(externalPrescriptionAddMedicationApiCall.getAddExternalMedication());
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

    public AddExternalMedication getAddExternalMedication() {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/AddExternalMedication.json"))) {
            Gson gson = new Gson();
            AddExternalMedication result = gson.fromJson(reader, AddExternalMedication.class);
            result.setId(getEncounterID());
            result.setPrescriptionNumber(getEncounterID());
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

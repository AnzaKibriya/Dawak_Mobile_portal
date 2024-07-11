package API_Calls;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.InsuranceCardVerification;
import model.VerifyExternalMedication;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static API_Calls.CPExternalPrescriptionTaskDetails.getExternalPrescriptionId;
import static API_Calls.CPExternalPrescriptionTaskDetails.getPrescriptionId;
import static API_Calls.GetCPTaskApiCall.getEncounterID;
import static Helper.BaseClass.client;
import static Helper.BaseClass.test;

public class VerifyExternalMedicationApiCall {
    static String apiUrl = BaseClass.propertyFile("config", "url") + "/dawak-portal/api/external-prescription/update-verify-medication";

    public static void makeVerifyExternalMedicationApiCall(String authToken) {
        try {
            test.log(Status.INFO, "Verify External Medication of the Patient");
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            VerifyExternalMedicationApiCall verifyExternalMedicationApiCall = new VerifyExternalMedicationApiCall();
            String jsonPayload = gson.toJson(verifyExternalMedicationApiCall.getVerifyExternalMedication());
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

    public VerifyExternalMedication getVerifyExternalMedication() {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/VerifyExternalMedication.json"))) {
            Gson gson = new Gson();
            VerifyExternalMedication result = gson.fromJson(reader, VerifyExternalMedication.class);
            result.setEncounterId(Integer.parseInt(getEncounterID()));
            result.setPrescriptionFileId(getPrescriptionId());
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

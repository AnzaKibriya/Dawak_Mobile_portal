package API_Calls;

import com.aventstack.extentreports.Status;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import static API_Calls.GetCPTaskApiCall.getEncounterID;
import static Helper.BaseClass.client;
import static Helper.BaseClass.test;

public class CPExternalPrescriptionTaskDetails {
    public static int prescriptionId;
    public static int externalPrescriptionId;
    public static int getPrescriptionId() {
        return prescriptionId;
    }

    public static void setPrescriptionId(int prescriptionId) {
        CPExternalPrescriptionTaskDetails.prescriptionId = prescriptionId;
    }

    public static int getExternalPrescriptionId() {
        return externalPrescriptionId;
    }

    public static void setExternalPrescriptionId(int externalPrescriptionId) {
        CPExternalPrescriptionTaskDetails.externalPrescriptionId = externalPrescriptionId;
    }

    public static void makeExternalPrescriptionTaskDetailsApiCall(String AUTH_TOKEN) {
        try {
            String apiUrl = "https://dawak-apim-uat.azure-api.net/dawak-portal/api/patient-refill/v3/get-task-detail/" + getEncounterID();
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                test.log(Status.PASS, "Insurance progress API called successfully");
                JSONObject jsonObject = new JSONObject(response.body().string());
                JSONArray dataArray = jsonObject.getJSONArray("data");
                System.out.println("Response: " + dataArray);
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataObject = dataArray.getJSONObject(i);
                    JSONArray medicationsArray = dataObject.getJSONArray("prescriptionFiles");
                    for (int j = 0; j < medicationsArray.length(); j++) {
                        JSONObject medicationObject = medicationsArray.getJSONObject(j);
                        int a = medicationObject.getInt("prescriptionId");
                        setPrescriptionId(medicationObject.getInt("prescriptionId"));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


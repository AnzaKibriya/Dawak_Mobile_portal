package API_Calls;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static Helper.BaseClass.client;
import static API_Calls.GetCPTaskApiCall.getEncounterID;
import static Helper.BaseClass.test;

public class InProgressInsuranceTaskDetailsApiCall {
    static String apiUrl= BaseClass.propertyFile("config","url")+"/dawak-portal/api/patient-refill/v3/get-task-detail/"+ getEncounterID();;
    public static List<Integer> makeInProgressInsuranceTaskDetailsApiCall(String AUTH_TOKEN){
        try {
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            List<Integer> medicationRequestIds = null;
            if (response.isSuccessful()) {
                test.log(Status.PASS, "Insurance progress API called successfully");

                medicationRequestIds = new ArrayList<>();
                // Return the list of medicationRequestIDs
                JSONObject jsonObject = new JSONObject(response.body().string());
                JSONArray dataArray = jsonObject.getJSONArray("data");
                System.out.println("Response: " + dataArray);
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataObject = dataArray.getJSONObject(i);
                    JSONArray medicationsArray = dataObject.getJSONArray("pendingMedications");

                    for (int j = 0; j < medicationsArray.length(); j++) {
                        JSONObject medicationObject = medicationsArray.getJSONObject(j);
                        int medicationRequestId = medicationObject.getInt("medicationRequestId");
                        medicationRequestIds.add(medicationRequestId);
                    }
                }
//                }
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
            return medicationRequestIds;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

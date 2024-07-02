package API_Calls;

import Helper.BaseClass;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetPatientApiCall extends BaseClass {

    static String patientID;
    static String apiUrl=BaseClass.propertyFile("config","url")+ "/dawak-patient/api/patient/get-patient-list";
    public static String getPatientApiCall(String AUTH_TOKEN) {
        try {

            Request request = new Request.Builder()
                    .url(apiUrl)
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                JSONArray dataArray = jsonResponse.getJSONArray("data");
                JSONObject dataObject = dataArray.getJSONObject(0);
                patientID = String.valueOf(dataObject.getInt("id"));
                System.out.println("Data deleted sucessfully");

            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
            return patientID;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static int getPatientID() {
        return Integer.parseInt(patientID);
    }
    public static void setEncounterIDDp(String patientID) {
        GetPatientApiCall.patientID = patientID;
    }
}

package API_Calls;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static Helper.BaseClass.client;
import static Helper.BaseClass.test;

public class GetCPTaskApiCall {
    public static int taskId;
    public static String encounterID;

    public static int getTaskId() {
        return taskId;
    }

    public static void setTaskID(int taskID) {
        GetCPTaskApiCall.taskId = taskID;
    }

    public static String getEncounterID() {
        return encounterID;
    }

    public static void setEncounterID(String encounterID) {
        GetCPTaskApiCall.encounterID = encounterID;
    }

    static String apiurl = "https://dawak-apim-uat.azure-api.net/dawak-portal/api/pharmacist/get-tasks";

    public static void getTaskApiCall(String authToken, String prescriptionID) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            String jsonPayload = "{\"filterParams\": {}}";
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiurl)
                    .header("Authorization", "Bearer " + authToken)
                    .post(RequestBody.create(jsonPayload, MediaType.parse("application/json")))
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                test.log(Status.PASS, "Get Task API called successfully");
                JSONObject jsonResponse = new JSONObject(response.body().string());
                System.out.println(jsonResponse);
                JSONArray dataArray = jsonResponse.getJSONArray("data");

                for (int i = 0; i <= dataArray.length(); i++) {
                    JSONObject dataObj = dataArray.getJSONObject(i);
                    String finNumber = dataObj.getString("finNumber");
                    if (finNumber.equals(prescriptionID)) {
                        setTaskID(dataObj.getInt("taskId"));
                        setEncounterID(dataObj.getString("encounterId"));
                        break;
                    }
                }

            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package model;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static Helper.BaseClass.client;

public class GetDPTaskApiCall {
    public static int taskIdDp;
    public static String encounterIDDp;

    public static int getTaskIdDp() {
        return taskIdDp;
    }

    public static void setTaskIDDp(int taskIDDp) {
        GetDPTaskApiCall.taskIdDp = taskIDDp;
    }

    public static String getEncounterIDDp() {
        return encounterIDDp;
    }

    public static void setEncounterIDDp(String encounterIDDp) {
        GetDPTaskApiCall.encounterIDDp = encounterIDDp;
    }

    static String apiurl = "https://dawak-apim-uat.azure-api.net/dawak-portal/api/dispensing-pharmacist/get-todo-tasks";

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
                JSONObject jsonResponse = new JSONObject(response.body().string());
                System.out.println(jsonResponse);
                JSONArray dataArray = jsonResponse.getJSONArray("data");

                for (int i = 0; i <= dataArray.length(); i++) {
                    JSONObject dataObj = dataArray.getJSONObject(i);
                    String finNumber = dataObj.getString("finNumber");
                    if (finNumber.equals(prescriptionID)) {
                        setTaskIDDp(dataObj.getInt("taskId"));
                        setEncounterIDDp(dataObj.getString("encounterId"));
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

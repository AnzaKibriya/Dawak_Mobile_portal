package API_Calls;

import Helper.BaseClass;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static API_Calls.GetDPTaskApiCall.getEncounterIDDp;
import static Helper.BaseClass.client;

public class GetShipaIdApiCall {

    static String shipaOrderNum;
    static String apiUrl = BaseClass.propertyFile("config","url")+ "/dawak-patient/api/shipa/get-delivery-details?encounterId=" + getEncounterIDDp();;

    public static String makeShipaIdApiCall(String AUTH_TOKEN) {
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
                shipaOrderNum = dataObject.getString("shippaOrderNum");
                System.out.println("Shippa Order Number: " + shipaOrderNum);

            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
            return shipaOrderNum;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

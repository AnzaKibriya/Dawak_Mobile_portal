package API_Calls;

import Helper.BaseClass;
import com.google.gson.Gson;
import model.DPClaimTask;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import static Helper.BaseClass.client;
import static API_Calls.GetDPTaskApiCall.getEncounterIDDp;
import static API_Calls.GetDPTaskApiCall.getTaskIdDp;

public class DPClaimTaskApiCall {
    static String apiUrl = BaseClass.propertyFile("config","url") +"/dawak-portal/api/dispensing-pharmacist/claim-task";;

    public static void getTaskClaimApiCall(String AUTH_TOKEN) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            DPClaimTaskApiCall claimTaskApiCall = new DPClaimTaskApiCall();
            String jsonPayload = gson.toJson(claimTaskApiCall.getClaimTask());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
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


    public DPClaimTask getClaimTask() {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/DPClaimTask.json"))) {
            Gson gson = new Gson();
            DPClaimTask result = gson.fromJson(reader, DPClaimTask.class);
            result.setTaskId(getTaskIdDp());
            result.setId(Integer.parseInt(getEncounterIDDp()));
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

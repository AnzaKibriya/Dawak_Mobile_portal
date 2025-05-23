package API_Calls;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.ClaimTask;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static API_Calls.GetCPTaskApiCall.*;
import static Helper.BaseClass.*;

public class CPClaimTaskApiCall {
    static String apiUrl = BaseClass.propertyFile("config","url")+ "/dawak-portal/api/pharmacist/claim-task"; ;

    public static void getTaskClaimApiCall(String AUTH_TOKEN) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            CPClaimTaskApiCall claimTaskApiCall = new CPClaimTaskApiCall();
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
                test.log(Status.PASS, "Get task API claim called successfully");
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


    public ClaimTask getClaimTask() {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/CPClaimTask.json"))) {
            Gson gson = new Gson();
            ClaimTask result = gson.fromJson(reader, ClaimTask.class);
            result.setTaskId(String.valueOf(getTaskId()));
            result.setId(Integer.parseInt(getEncounterID()));
            result.setEncounterId(prescriptionOrderID);//prescriptionOrderID
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


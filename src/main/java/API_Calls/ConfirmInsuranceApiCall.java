package API_Calls;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.ConfirmInsurance;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static API_Calls.GetCPTaskApiCall.getEncounterID;
import static API_Calls.GetCPTaskApiCall.getTaskId;
import static Helper.BaseClass.*;

public class ConfirmInsuranceApiCall  {
    static String apiUrl = BaseClass.propertyFile("config","url")+ "/dawak-portal/api/pharmacist/confirm-insurance"; ;
    public static void getConfirmInsuranceApiCall(String AUTH_TOKEN) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            ConfirmInsuranceApiCall confirmInsuranceApiCall = new ConfirmInsuranceApiCall();
            String jsonPayload = gson.toJson(confirmInsuranceApiCall.getConfirmInsurance());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                test.log(Status.PASS, "Insurance conformation API called successfully");
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


    public ConfirmInsurance getConfirmInsurance() {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/ConfirmInsurance.json"))) {
            Gson gson = new Gson();
            ConfirmInsurance result = gson.fromJson(reader, ConfirmInsurance.class);
            result.setTaskId(String.valueOf(getTaskId()));
            result.setId(Integer.parseInt(getEncounterID()));
            result.setPrescriptionNumber(prescriptionOrderID);
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package API_Calls;

import Helper.BaseClass;
import com.google.gson.Gson;
import model.DispensingStarted;
import model.RefillRequest;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static API_Calls.GetDPTaskApiCall.getEncounterIDDp;
import static API_Calls.NewPatientApiCall.generateRandomEID;
import static Helper.BaseClass.client;

public class DeletePatientApiCall extends BaseClass {


    public static void deletePatientApiCall(String AUTH_TOKEN) {
        try {
            prescriptionOrderID = generateRandomNumericString();
            System.out.println(prescriptionOrderID);
            String apiUrl = "https://dawak-apim-uat.azure-api.net/dawak-patient/api/patient/remove-patient/" + GetPatientApiCall.getPatientID();
            ;
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("Data deleted sucessfully");
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



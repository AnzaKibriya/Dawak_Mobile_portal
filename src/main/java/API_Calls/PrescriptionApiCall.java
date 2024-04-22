package API_Calls;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.PrescriptionRequest;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Helper.BaseClass.client;
import static Helper.BaseClass.test;

public class PrescriptionApiCall {
    private static final String API_URL = "https://dawak-apim-uat.azure-api.net/dawak-portal/api/prescription/new";

    public static void makePrescriptionApiCall(String AUTH_TOKEN, String orderID) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            PrescriptionApiCall prescriptionApiCall = new PrescriptionApiCall();
            String jsonPayload = gson.toJson(prescriptionApiCall.getPrescriptionRequest(orderID));
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            int  a = response.code();
            if (response.isSuccessful()) {
                test.log(Status.PASS, "Make prescription API called successfully");
                System.out.println("API call successful!");
                System.out.println("Response: " + response.body().string());
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PrescriptionRequest getPrescriptionRequest(String orderID) {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/CreatingOrder.json"))) {
            Gson gson = new Gson();
            PrescriptionRequest result = gson.fromJson(reader, PrescriptionRequest.class);
            result.getOrder().setPhysicianEncounterId(orderID);
            result.getOrder().setPhysicianOrderDate(getCurrentDateTime());
            result.getOrder().setOrderVisitDate(getCurrentDateTime());
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(formatter);
    }

}

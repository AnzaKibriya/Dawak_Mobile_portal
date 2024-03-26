package model;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static Helper.BaseClass.client;
import static Helper.BaseClass.emiratesID;

public class NewPatientApiCall {

    private static final String API_URL = "https://dawak-apim-uat.azure-api.net/dawak-portal/api/prescription/new";

    public static void makeCreatePatientApiCall(String AUTH_TOKEN, String orderID) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            NewPatientApiCall NewPatientApiCall = new NewPatientApiCall();
            String jsonPayload = gson.toJson(NewPatientApiCall.getPrescriptionRequest(orderID));
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
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/CreateNewPatient.json"))) {
            Gson gson = new Gson();
            PrescriptionRequest result = gson.fromJson(reader, PrescriptionRequest.class);
            result.getOrder().setPhysicianEncounterId(orderID);
            result.getOrder().setPhysicianOrderDate(getCurrentDateTime());
            result.getOrder().setOrderVisitDate(getCurrentDateTime());
            result.getPatient().setEid("784"+ generateRandomEID());
            result.getPatient().setMrn(orderID);
            result.getPatient().setCmrn(orderID);
            result.getPatient().setPhoneNumber("9715"+ orderID);
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

    public static String generateRandomEID() {
        int length = 12;
        StringBuilder numericString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            emiratesID = String.valueOf(numericString.append(digit));
        }
        return emiratesID;
    }
}

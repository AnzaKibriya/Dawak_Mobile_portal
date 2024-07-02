package API_Calls;

import Helper.BaseClass;
import com.google.gson.Gson;
import model.PrescriptionRequest;
import model.RefillRequest;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static API_Calls.NewPatientApiCall.generateRandomEID;
import static Helper.BaseClass.client;

public class RefillsApiCall {
    private static final String API_URL = BaseClass.propertyFile("config","url")+"/dawak-portal/api/prescription/detail";

    public static void makeRefillsApiCall(String AUTH_TOKEN, String orderID) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            RefillsApiCall refillApiCall = new RefillsApiCall();
            String jsonPayload = gson.toJson(refillApiCall.getRefillsRequest(orderID));
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

    public RefillRequest getRefillsRequest(String orderID) {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/CreateNewRefillItem.json"))) {
            Gson gson = new Gson();
            RefillRequest result = gson.fromJson(reader, RefillRequest.class);

            result.getRefillsOrder().setPharmacyEncounterId(orderID);
            result.getRefillsOrder().setPhysicianEncounterId(orderID);
            result.getRefillsOrder().getMedications().get(0).setPhysicianOrderId(orderID);
            result.getRefillsOrder().getMedications().get(0).setDispensedOrderId(orderID);
            result.getRefillsOrder().getMedications().get(0).setLastDispDate(getCurrentDateTime());
            result.getRefillsOrder().getMedications().get(0).setNextRefillDate(getCurrentDateTime());
            result.getRefillsOrder().setPhysicianOrderDate(getCurrentDateTime());
            result.getRefillsOrder().setOrderVisitDate(getCurrentDateTime());
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

}

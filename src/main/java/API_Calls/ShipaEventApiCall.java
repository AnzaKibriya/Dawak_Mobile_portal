package API_Calls;

import com.google.gson.Gson;
import model.ShipaEvent;
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

public class ShipaEventApiCall {
    private static final String API_URL = "https://dawak-apim-uat.azure-api.net/dawak-patient/api/shipa/webhook";

    public static void makeShipaEventApiCall(String AUTH_TOKEN, String orderID, String event) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            ShipaEventApiCall shipaInitiateEventApiCall = new ShipaEventApiCall();
            String jsonPayload = gson.toJson(shipaInitiateEventApiCall.shipaEventRequest(orderID, event));
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            int  a = response.code();
            assert response.body() != null;
            String responseString = response.body().string();
            if (responseString.contains("Notification")){
                System.out.println("API call successful!");
                System.out.println("Response: " + response.body().string());
            }
            else {
                System.out.println("API call failed!");
            }

//            if (response.isSuccessful()) {
//                System.out.println("API call successful!");
//                System.out.println("Response: " + response.body().string());
//            } else {
//                System.out.println("API call failed!");
//                System.out.println("Response: " + response.body().string());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ShipaEvent shipaEventRequest(String orderID, String event) {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/ShipaInitiateEvent.json"))) {
            Gson gson = new Gson();
            ShipaEvent result = gson.fromJson(reader, ShipaEvent.class);
            result.setShipaRef(orderID);
            result.setDate(getCurrentDateTime());
            result.setEvent("order.dropoff."+ event);
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
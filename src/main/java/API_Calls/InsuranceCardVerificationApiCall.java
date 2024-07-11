package API_Calls;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import model.InsuranceCardVerification;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static API_Calls.CPExternalPrescriptionTaskDetails.getExternalPrescriptionId;
import static Helper.BaseClass.*;

public class InsuranceCardVerificationApiCall {
    static String apiUrl = BaseClass.propertyFile("config", "url") + "/dawak-portal/api/external-prescription/verify";

    public static void makeInsuranceCardVerificationAPICall(String cardSide, String authToken) {
        try {
            test.log(Status.INFO, "Verify Insurance Card of the Patient");
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            InsuranceCardVerificationApiCall insuranceCardVerificationAPIApiCall = new InsuranceCardVerificationApiCall();
            int result = cardSide.contains("Front") ? 1 : (cardSide.contains("Back") ? 2 : 0);
            String jsonPayload = gson.toJson(insuranceCardVerificationAPIApiCall.getInsuranceCardVerification(result));
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .header("Authorization", "Bearer " + authToken)
                    .addHeader("Content-Type", "application/json")
                    .post(RequestBody.create(jsonPayload, MediaType.parse("application/json")))
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

    public InsuranceCardVerification getInsuranceCardVerification(int fileType) {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/InsuranceCardVerification.json"))) {
            Gson gson = new Gson();
            InsuranceCardVerification result = gson.fromJson(reader, InsuranceCardVerification.class);
            result.setExternalPrescriptionId(getExternalPrescriptionId());
            result.setFileType(fileType);
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}


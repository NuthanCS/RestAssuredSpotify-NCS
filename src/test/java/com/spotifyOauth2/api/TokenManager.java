package com.spotifyOauth2.api;

import com.spotifyOauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotifyOauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String accessToken;
    private static Instant expiryTime;


    private static Response renewToken()
    {
        HashMap<String, String> formParameters = new HashMap<>();
        formParameters.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParameters.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        formParameters.put("client_id",ConfigLoader.getInstance().getClientId());
        formParameters.put("client_secret",ConfigLoader.getInstance().getClientSecret());

        Response response = RestResource.postAccount(formParameters);
        return response;


//        if(response.statusCode()!=200)
//        {
//            throw new RuntimeException("ABORT Since token is invalid");
//        }
//        else {
//            return response;
//
//        }
    }

    public static String getToken()
    {
        try
        {
            if(accessToken == null || Instant.now().isAfter(expiryTime))
            {
                System.out.println("Renewing access token");
                Response response = renewToken();
                accessToken = response.path("access_token");
                int expiryDurationTime = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryDurationTime-200);
            }
            else
            {
                System.out.println("Token is valid");
            }

        }
        catch(Exception e)
        {
            throw new RuntimeException("Token is Invalid or expired so ABORT");
        }

        return accessToken;

    }
}

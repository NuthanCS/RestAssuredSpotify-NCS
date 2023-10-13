package com.spotifyOauth2.api;

import com.spotifyOauth2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotifyOauth2.api.Route.API;
import static com.spotifyOauth2.api.Route.TOKEN;
import static com.spotifyOauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;


//All the Rest Assured entities will be present in this class which is reusable
public class RestResource {
    public static Response post(Object requestPlaylist, String path, String token)
    {
        return given(getRequestSpec()).
                body(requestPlaylist).
                auth().oauth2(token).
               // header("Authorization", "Bearer "+token).
                when().
                post(path).
                then().spec(getResponseSpec())
                .extract().response();
    }


    public static Response get(String path, String token)
    {
        return given(getRequestSpec()).
                auth().oauth2(token).
                //header("Authorization", "Bearer "+token).
                when().get(path).
                then().
                spec(getResponseSpec()).
                extract().response();
    }

    public static Response put(Object updatedRequestPlaylist, String path, String token)
    {
        return given(getRequestSpec()).
                auth().oauth2(token).
                //header("Authorization", "Bearer "+token).
                when().
                body(updatedRequestPlaylist).
                put(path).
                then().
                spec(getResponseSpec()).
                extract().response();
    }

    public static Response postAccount(HashMap<String, String> formParameters)
    {
        return given(getAccountRequestSpec()).
                formParams(formParameters).
                when().
                post(API+TOKEN).
                then().
                spec(getResponseSpec()).
                extract().response();
    }
}

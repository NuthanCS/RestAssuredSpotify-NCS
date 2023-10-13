package com.spotifyOauth2.api.applicationApi;

import com.spotifyOauth2.api.RestResource;
import com.spotifyOauth2.pojo.Playlist;
import com.spotifyOauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotifyOauth2.api.Route.PLAYLISTS;
import static com.spotifyOauth2.api.Route.USERS;
import static com.spotifyOauth2.api.TokenManager.getToken;



public class PlaylistApi {

    //static String accessToken = "Bearer BQDFOsolUDPV3gTZM4Mn75ELax7dHjIWL5FDqtgXr3semUm05XRZOJd42AQjDXyfN6JnAqE0GVVQJuCDgBJm4rOgRbx-FHGRUTOG9Udf7LA32rmgAfMjJi9vMdXjnapUm2hvRZrGG228NxlZ5ngynmmZBPms9woiWaxDRm8Vh1LWKcgEJEdRAE1lmdV9pW8oNl2H9D0dxe-nwsLoKyLywGcU5PwXgEbqT6FYyKUahTvbCAoee818I8RLZsd9ksCg2ftdBAyIsQFEgFHw";

    @Step
    public static Response post(Playlist requestPlaylist)
    {
        return RestResource.post(requestPlaylist,USERS+"/"+ConfigLoader.getInstance().getUserId()+PLAYLISTS,getToken());

    }

    public static Response post(String token , Playlist requestPlaylist)
    {

        return RestResource.post(requestPlaylist,USERS+"/"+ConfigLoader.getInstance().getUserId()+PLAYLISTS,token);

    }

    public static Response get(String playlistID)
    {

        return RestResource.get(PLAYLISTS+"/"+playlistID, getToken());

    }

    public static Response put(Playlist updatedRequestPlaylist, String playlistID)
    {

       return RestResource.put(updatedRequestPlaylist,PLAYLISTS+"/"+playlistID, getToken());

    }
}

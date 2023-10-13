package com.spotifyOauth2.tests;

import com.spotifyOauth2.api.StatusCode;
import com.spotifyOauth2.api.applicationApi.PlaylistApi;
import com.spotifyOauth2.pojo.Error;
import com.spotifyOauth2.pojo.Playlist;
import com.spotifyOauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.spotifyOauth2.api.SpecBuilder.getRequestSpec;
import static com.spotifyOauth2.api.SpecBuilder.getResponseSpec;
import static com.spotifyOauth2.utils.FakerUtils.generateDescription;
import static com.spotifyOauth2.utils.FakerUtils.generateName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Oauth2.0")// this will help to execute at the class level and create separate column in the report
@Feature("Playlist")// this will indicate the feature test that we are executing
public class PlaylistTests extends BaseTest{

    @Story("create a new playlist")// with the help of this we can club common test cases
    @Link("https://example.org")// it indicates our report url
    @Link(name="allure",type="mylink")
    @TmsLink("12345")// it will provide the link to that particular test case which has falied
    @Issue("987")// it will provide the link the particular defect
    @Description("it is going to create a new playlist for the user Nuthan")//it is going to add Description field and value in the allure reports for this particular test case
    @Test(description = "Creating a new Playlist")//this will be the name of the test case in the allure reports
    public void createPlaylist()
    {
        Playlist requestPlaylist = playlistBuilder(generateName(),generateDescription(), false );

        //we are using generic method i.e Post from PlaylistApi class and store it in a response variable since it is return type is Response
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCodeEquals(response.getStatusCode(), StatusCode.CODE_201);

        //Deserialization for assertion
        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEquals(responsePlaylist,requestPlaylist);
    }

    @Test
    public void getAPlaylist()
    {
        Playlist reuestPlaylist = playlistBuilder("Modified Playlist Name","Updated playlist description", false);

        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCodeEquals(response.getStatusCode(),StatusCode.CODE_200);

        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEquals(responsePlaylist,reuestPlaylist);
    }

    @Test
    public void updatePlaylist()
    {
        Playlist updatedRequestPlaylist =
                playlistBuilder(generateName(),generateDescription(),false);

        Response response = PlaylistApi.put(updatedRequestPlaylist,DataLoader.getInstance().getUpdatePlaylistId());
        assertStatusCodeEquals(response.getStatusCode(),StatusCode.CODE_200);
    }

    @Story("create a new playlist")
    @Test
    public void createPlaylistWithoutName()
    {
        Playlist requestPlaylistWithoutName = playlistBuilder("",generateDescription(),false);

        Response response = PlaylistApi.post(requestPlaylistWithoutName);
        assertStatusCodeEquals(response.getStatusCode(),StatusCode.CODE_400);

        //we need to deserialization for Error pojo class since response will be stored under this
        Error error = response.as(Error.class);
        assertErrorEquals(error,StatusCode.CODE_400);
    }

    @Story("create a new playlist")
    @Test
    public void createPlaylistWithExpiredAccessToken()
    {
        String token = "Bearer 1234";
        Playlist requestPlaylistWithExpiredToken =
                playlistBuilder(generateName(),generateDescription(),false);

        Response response = PlaylistApi.post(token, requestPlaylistWithExpiredToken);
        assertStatusCodeEquals(response.getStatusCode(),StatusCode.CODE_401);

        Error error = response.as(Error.class);
        assertErrorEquals(error,StatusCode.CODE_401);
    }

    @Step//this will add separate column called as Execution>Test body and the display the methods that we have used,this will help in debugging
    public Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder().name(name).description(description)._public(_public).build();
    }

    @Step
    public void assertPlaylistEquals(Playlist responsePlaylist, Playlist requestPlaylist)
    {
        assertThat(responsePlaylist.getName(),equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(),equalTo(requestPlaylist.get_public()));
    }

    @Step
    public void assertStatusCodeEquals(int actualStatusCode, StatusCode statusCode)
    {
        assertThat(actualStatusCode,equalTo(statusCode.code));
    }

    @Step
    public void assertErrorEquals(Error error, StatusCode statusCode)
    {
        assertThat(error.getInnerError().getStatus(),equalTo(statusCode.code));
        assertThat(error.getInnerError().getMessage(),equalTo(statusCode.msg));
    }

}

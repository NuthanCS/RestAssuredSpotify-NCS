package com.spotifyOauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotifyOauth2.api.Route.BASE_PATH;

public class SpecBuilder {
    public static RequestSpecification getRequestSpec()
    {
        return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build(); //build method returns the requestSpecBuilder of Request Specification
    }

    public static RequestSpecification getAccountRequestSpec()
    {
        return new RequestSpecBuilder().
                setBaseUri("https://accounts.spotify.com").
                setContentType(ContentType.URLENC).
                addFilter(new AllureRestAssured()).// this will display entire request API, Headers and responses in the allure report
                log(LogDetail.ALL).
                build(); //build method returns the requestSpecBuilder of Request Specification
    }

    public static ResponseSpecification getResponseSpec()
    {
        return new ResponseSpecBuilder().
               // expectContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build(); // build method returns the responseSpecBuilder of ResponseSpecification
    }

}

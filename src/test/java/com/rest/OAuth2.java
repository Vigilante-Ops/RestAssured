package com.rest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class OAuth2
{
    private String accessToken=null;
    @BeforeClass
    public void init()
    {
        RestAssured.baseURI="https://www.googleapis.com/oauth2/v4/token";
    }

    @Test(priority = 0)
    public void getAccessCode()
    {
        HashMap<String,String> queryParams=new HashMap<>();
        queryParams.put("code","4%2F0AfJohXngWvQ_LN3PpB8s16XI7ZXQ-OYRM-gorJ9R-K48oqGOPLIrEHgHLsCNs86IF9S61A");
        queryParams.put("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
        queryParams.put("client_secret","erZOWM9g3UtwNRj340YYaK_W");
        queryParams.put("redirect_uri","https://rahulshettyacademy.com/getCourse.php");
        queryParams.put("grant_type","authorization_code");

        String response=RestAssured.given().queryParams(queryParams).urlEncodingEnabled(false).log().all().
                when().post().then().extract().response().asString();
        JsonPath jp=new JsonPath(response);
        accessToken=jp.getString("access_token");


    }
    @Test(priority = 1)
    public void actualResource()
    {
        String response=RestAssured.given().baseUri("https://rahulshettyacademy.com/getCourse.php").queryParam("access_token",accessToken).urlEncodingEnabled(false).log().all().
                when().get().then().extract().response().asString();
        System.out.println(response);
    }


}

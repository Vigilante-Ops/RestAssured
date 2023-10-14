package com.rest;

import io.restassured.RestAssured;
import  static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Tester
{
    @BeforeClass
    public void init()
    {
        RestAssured.baseURI="https://rahulshettyacademy.com/";
    }
    private JsonPath jp=null;
    private String place_id=null;
  //  @Test(testName = "demo")
    public void TestSanity()
    {
      given().queryParam("key","qaclick123").contentType("application/json").
                body("{\n" +
                        "    \"location\": {\n" +
                        "        \"lat\": -38.383494,\n" +
                        "        \"lng\": 33.427362\n" +
                        "    },\n" +
                        "    \"accuracy\": 50,\n" +
                        "    \"name\": \"Frontline house\",\n" +
                        "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "    \"address\": \"29, side layout, cohen 09\",\n" +
                        "    \"types\": [\n" +
                        "        \"shoe park\",\n" +
                        "        \"shop\"\n" +
                        "    ],\n" +
                        "    \"website\": \"http://google.com\",\n" +
                        "    \"language\": \"French-IN\"\n" +
                        "}").
                when().post("maps/api/place/add/json").
                then().assertThat().statusCode(200).body("scope",equalTo("APP")).
              header("Server","Apache/2.4.52 (Ubuntu)");

    }

    @Test(testName = "Add Place")
    public void addPlace()
    {
      String response=  given().queryParam("key","qaclick123").contentType("application/json").
                body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}").
                when().post("maps/api/place/add/json").
                then().assertThat().statusCode(200).and().extract().response().asString();
                 jp=new JsonPath(response);
                place_id=jp.getString("place_id");



    }

    @Test(testName = "get Place")
    public void getPlace()
    {
        String response=  given().queryParam("key","qaclick123").
                queryParam("place_id",place_id).contentType("application/json").
                when().get("maps/api/place/get/json").
                then().assertThat().statusCode(200).and().extract().response().asString();
        jp=new JsonPath(response);
        System.out.println(jp.getString("location.latitude"));


    }
}

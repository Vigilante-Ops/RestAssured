package com.rest;

import io.restassured.RestAssured;
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
    @Test(testName = "demo")
    public void TestV()
    {
      String response=  given().queryParam("key","qaclick123").contentType("application/json").
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
                then().assertThat().statusCode(200).and().extract().response().asString();
        System.out.println(response);

    }
}

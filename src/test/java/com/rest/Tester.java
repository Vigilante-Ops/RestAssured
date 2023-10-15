package com.rest;

import MockingSection6.Course;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import  static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
    private PUTBody putBody=null;
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

    @Test(testName = "Add Place",priority = 0)
    public void addPlace() throws IOException {
      String response=  given().queryParam("key","qaclick123").contentType("application/json").
                body(new String(Files.readAllBytes(Path.of("D:\\000Starting new\\RestAssured\\Data.json")))).
                when().post("maps/api/place/add/json").
                then().assertThat().statusCode(200).and().extract().response().asString();
                 jp=new JsonPath(response);
                place_id=jp.getString("place_id");
        System.out.println(response);




    }


    @Test(testName = "PUT Place",priority = 1)
    public void putPlace()
    {
        putBody=new PUTBody();
        putBody.setAddress("lmao");
        putBody.setPlace_id(this.place_id);
        putBody.setKey("qaclick123");

        String response=  given().queryParam("key","qaclick123").contentType("application/json").
                body(putBody).
                when().put("maps/api/place/update/json").
                then().assertThat().statusCode(200).and().extract().response().asString();
        System.out.println(response);


    }


    @Test(testName = "get Place",priority = 2)
    public void getPlace()
    {
        GETResponse response=  given().queryParam("key","qaclick123").
                queryParam("place_id",place_id).contentType("application/json").
                when().get("maps/api/place/get/json").
                then().assertThat().statusCode(200).and().extract().response().as(GETResponse.class);
        System.out.println(response.getLocation().getLatitude());
        //jp=new JsonPath(response);
       // System.out.println(jp.getString("address"));
       // Assert.assertEquals(jp.getString("address"),putBody.getAddress());

    }
    @Test(testName = "sectionsixmock")
    public void SectionSixMock()
    {
        jp=new JsonPath("{\n" +
                "\n" +
                "\"dashboard\": {\n" +
                "\n" +
                "\"purchaseAmount\": 910,\n" +
                "\n" +
                "\"website\": \"rahulshettyacademy.com\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"courses\": [\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Selenium Python\",\n" +
                "\n" +
                "\"price\": 50,\n" +
                "\n" +
                "\"copies\": 6\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Cypress\",\n" +
                "\n" +
                "\"price\": 40,\n" +
                "\n" +
                "\"copies\": 4\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"RPA\",\n" +
                "\n" +
                "\"price\": 45,\n" +
                "\n" +
                "\"copies\": 10\n" +
                "\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "\n" +
                "}\n" +
                "\n");

        /*List<Course> courses= jp.getList("courses");
        ObjectMapper mapper=new ObjectMapper();
        List<Course> c=mapper.convertValue(courses, new TypeReference<List<Course>>() {
        });*/
        int count=jp.getInt("courses.size()");
        int price=0;
        int pamount=jp.getInt("dashboard.purchaseAmount");
        for (int i=0;i<count;i++)
        {
            price=price+ (jp.getInt("courses.get("+i+").price")*jp.getInt("courses.get("+i+").copies"));
        }
        Assert.assertEquals(pamount,price);








    }
}

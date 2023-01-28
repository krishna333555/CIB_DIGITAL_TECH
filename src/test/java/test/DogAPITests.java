package test;

import com.api.Dogs;
import com.api.Message;
import com.api.SubListMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class DogAPITests {

    @Test(description = "Perform an API request to produce a list of all dog breeds.")
    public void getListOfAllDogsBreeds() throws IOException {

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://dog.ceo/api/breeds/list/all")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
        //System.out.println(response.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        Dogs dogs = objectMapper.readValue(response.getBody().print(), Dogs.class);
        Message message = dogs.getMessage();
        //Using code, verify “retriever” breed is within the list.
        Assert.assertTrue(message.getRetriever().size() > 0, "Retriever is missing on response");


    }

    @Test(description = "Perform an API request to produce a list of sub-breeds for “retriever”")
    public void getSubBreeds() throws IOException {

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://dog.ceo/api/breed/retriever/list")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        SubListMessage subListMessage = objectMapper.readValue(response.getBody().print(), SubListMessage.class);
        List<String> listNames = subListMessage.getMessage().getRetriever();

        Assert.assertEquals(listNames.get(0), "chesapeake");
        Assert.assertEquals(listNames.get(1), "curly");
        Assert.assertEquals(listNames.get(2), "flatcoated");
        Assert.assertEquals(listNames.get(3), "golden");

    }

    @Test(description = "Perform an API request to produce a random image / link for the sub-breed “golden”")
    public void getSubBreedsRandomImage() throws IOException {

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://dog.ceo/api/breed/retriever/golden/images/random")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPath = new JsonPath(response.getBody().print());
        String image = jsonPath.getString("message");
        Assert.assertTrue(image.contains("retriever-golden"), "missing th retriever-golden on message");
    }
}

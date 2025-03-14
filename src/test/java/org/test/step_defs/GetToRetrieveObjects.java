package org.test.step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.test.Globals;

import java.util.List;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class GetToRetrieveObjects extends Globals {

    private static void getObjectDetailsUsingId(String id) {
        actualResponse = given()
                .queryParam("id", id)
                .when()
                .get(basePath);
        actualJsonResponse = actualResponse.jsonPath();
        System.out.println("What is the response body" + actualResponse.body().prettyPrint());
    }

    @When("I retrieve the new object created")
    public void iRetrieveTheNewObjectCreated() {
        getObjectDetailsUsingId(id);
    }

    @When("I retrieve all the objects")
    public void iRetrieveAllTheObjects() {
        actualResponse = given()
                .when()
                .get(basePath);
        actualJsonResponse = actualResponse.jsonPath();
        System.out.println("What is the response body" + actualResponse.body().prettyPrint());
    }

    @And("the retrieve response body should contain the object with name {string}")
    public void theRetrieveResponseBodyShouldContainTheObjectWithName(String name) {
        Assert.assertEquals(name, actualJsonResponse.getString("[0].name"));
    }

    @And("the response body should not be empty")
    public void theResponseBodyShouldNotBeEmpty() {
        List<Object> listOfIds = actualJsonResponse.getList("id");
        Assert.assertFalse(listOfIds.isEmpty());
    }

    @Given("I retrieve a non existing object")
    public void iRetrieveANonExistingObject() {
        getObjectDetailsUsingId("non-existing");
    }

    @And("the response body should be empty")
    public void theResponseBodyShouldBeEmpty() {
        List<Object> listOfObjects = actualJsonResponse.getList("$");
        Assert.assertTrue(listOfObjects.isEmpty());
    }
}

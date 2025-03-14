package org.test.step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.test.Globals;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class DeleteToDeleteObjects extends Globals {


    @When("I delete the object created above")
    public void iDeleteTheObjectCreatedAbove() {
        actualResponse = given()
                .header("Content-Type", "application/json")
                .delete(basePath + "/" + id);

        System.out.println("What is the response body" + actualResponse.body().prettyPrint());

        actualJsonResponse = actualResponse.jsonPath();
    }

    @And("I validate the message received")
    public void iValidateTheMessageReceived() {
        Assert.assertEquals(actualJsonResponse.get("message"), "Object with id = " + id + " has been deleted.");
    }
}

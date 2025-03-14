package org.test.step_defs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.test.Globals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostToCreateObjects extends Globals {
//    private Response actualResponse;
//    private JsonPath actualJsonResponse;

    @Given("I create a new object with valid data")
    public void i_create_a_new_object_with_valid_data(List<Map<String, Object>> dataTable) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", dataTable.getFirst().get("name"));
        requestBody.put("data", Map.of("year", dataTable.getFirst().get("year"),
                "category", dataTable.getFirst().get("category")));

        actualResponse = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(basePath);

        System.out.println("What is the response body" + actualResponse.body().prettyPrint());

        actualJsonResponse = actualResponse.jsonPath();
        id = actualJsonResponse.getString("id");
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, actualResponse.statusCode());
    }

    @Then("the response body should contain the object with year {string}")
    public void verifyResponseBodyContainsObjectWithYear(String year) {
        Assert.assertEquals(year, actualJsonResponse.getString("data.year"));
    }

    @Then("the response body should contain the object with name {string}")
    public void verifyResponseBodyContainsObjectWithName(String name) {
        Assert.assertEquals(name, actualJsonResponse.getString("name"));
    }

    @When("I get the object with ID {string}")
    public void getObjectByIdRequest(String id) {
        actualResponse = given()
                .when()
                .get(basePath + "/" + id);
    }

    @Then("the response body should contain the object with ID {string}")
    public void verifyResponseBodyContainsObjectWithId(String id) {
        actualResponse.then().body("id", equalTo(id));
    }

    @When("I delete the object with ID {string}")
    public void deleteObjectById(String id) {
        actualResponse = given()
                .when()
                .delete(basePath + "/" + id);
    }
}

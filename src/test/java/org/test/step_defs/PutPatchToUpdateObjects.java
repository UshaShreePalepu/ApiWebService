package org.test.step_defs;

import io.cucumber.java.en.When;
import org.test.Globals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class PutPatchToUpdateObjects extends Globals {


    @When("I update the following details of the object created")
    public void iUpdateTheFollowingDetailsOfTheObjectCreated(List<Map<String, Object>> dataTable) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", dataTable.getFirst().get("name"));
        requestBody.put("data", Map.of("year", dataTable.getFirst().get("year"),
                "category", dataTable.getFirst().get("category")));
        actualResponse = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put(basePath + "/" + id);

        System.out.println("What is the response body" + actualResponse.body().prettyPrint());

        actualJsonResponse = actualResponse.jsonPath();
    }

    @When("I update the details of an object with null id")
    public void iUpdateTheDetailsOfANonExistingObject(List<Map<String, Object>> dataTable) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", dataTable.getFirst().get("name"));
        requestBody.put("data", Map.of("year", dataTable.getFirst().get("year"),
                "category", dataTable.getFirst().get("category")));
        actualResponse = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put(basePath + "/" + null);
        System.out.println("What is the response body" + actualResponse.body().prettyPrint());
    }

    @When("I partially update the following details")
    public void iPatiallyUpdateTheFollowingDetails(List<Map<String, Object>> dataTable) {
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("data", Map.of("year", dataTable.getFirst().get("year")));
        actualResponse = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .patch(basePath + "/" + id);

        System.out.println("What is the response body" + actualResponse.body().prettyPrint());

        actualJsonResponse = actualResponse.jsonPath();
    }
}

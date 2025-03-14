package org.test;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
//import org.junit.Before;

public class Hooks {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://api.restful-api.dev/objects";
    }
}

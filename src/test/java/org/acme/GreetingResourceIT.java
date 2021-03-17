package org.acme;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(PostgresResource.class)
public class GreetingResourceIT {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello-resteasy")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy Reactive"));
    }

    @Test
    public void testTodosEndpoint() {
        given()
                .when().get("/todos")
                .then()
                .statusCode(200)
                .body("$.size()", is(4));
    }
}
package git.marcos;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class ReqresChallenge {

    @Before
    public void baseUrlAPI(){
        //Config the accesses common path and my API rest
        baseURI = "https://reqres.in";
        basePath = "/api";
    }

    @Test
    public void testGetUserById(){
        //Add travel
        given()
                .when()
                .get("/users/2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);
    }
}

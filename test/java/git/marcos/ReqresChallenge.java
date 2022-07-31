package git.marcos;

import io.restassured.http.ContentType;
import org.json.JSONObject;
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
    public void testStatusCodeGetExistingUserById(){
        //Get existing user
        given()
                .when()
                .get("/users/2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testStatusCodeGetNonExistingUserById(){
        //Get non existing user
        given()
                .when()
                .get("/users/23")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404);
    }

    @Test
    public void testStatusCodeListUsers(){
        given()
                .when()
                .get("/users?page=1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testStatusCodeCreateUser(){
        given()
                .body("{\n" +
                        "    \"name\": \"Marcos Paulo\",\n" +
                        "    \"job\": \"QA leader\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/users")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void testStatusCodeUpdateUser(){
        JSONObject request = new JSONObject();

        request.put("name","Marcos Paulo");
        request.put("job", "QA Leader");

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toString())
                .when()
                .put("/users/2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testStatusCodeDeleteUser(){
        given()
                .when()
                .delete("/users/8151")
                .then()
                .statusCode(204);
    }
}

package services.implementations;

import groovy.json.JsonParser;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import services.interfaces.IUserService;

import static io.restassured.RestAssured.given;

public class UserService implements IUserService {

    public UserService(String baseUrl, String urlPath) {
        _baseUrl = baseUrl;
        _urlPath =  urlPath;
    }

    private final String _baseUrl;
    private final String _urlPath;

    public RequestSpecification RequestSpecification() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(_baseUrl);
        builder.setBasePath(_urlPath);
        builder.addHeader("Content-Type", "application/json");
        builder.addHeader("Accet", "application/json");

        return builder.build();
    }

    public JsonPath GetListOfUsersAsJSON() {
        Response response = given()
                .spec(RequestSpecification())
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        response.jsonPath().prettyPrint();
        return response.jsonPath();
    }

    public ValidatableResponse GetListOfUsersValidatableResponse() {
        Response response = given()
                .spec(RequestSpecification())
                .get();

        return response.then();
    }

    public String GetListOfUsersAsString() {
        String response = given()
                .spec(RequestSpecification())
                .get()
                .asString();

        return response;
    }

    public void CheckResponseStatusCode(ValidatableResponse response, Integer statusCode) {
        response.statusCode(statusCode);
    }

    public void CheckResponseTime(ValidatableResponse response, Long milliSeconds) {
        response.time(Matchers.lessThan(milliSeconds));
    }

    public JsonPath getResponseJsonData(ValidatableResponse response) {
        Response getResponse = response.extract().response();
        return getResponse.jsonPath();
    }
}

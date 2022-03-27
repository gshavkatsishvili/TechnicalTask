package services.interfaces;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public interface IUserService {

    RequestSpecification RequestSpecification();

    JsonPath GetListOfUsersAsJSON();

    ValidatableResponse GetListOfUsersValidatableResponse();

    String GetListOfUsersAsString();

    void CheckResponseStatusCode(ValidatableResponse response, Integer statusCode);

    void CheckResponseTime(ValidatableResponse response, Long milliSeconds);

    JsonPath getResponseJsonData(ValidatableResponse response);
}

package services.interfaces;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public interface IUserService {

    RequestSpecification RequestSpecification();

    JsonPath GetListOfUsersAsJSON();

    @Step("Get Response Data")
    ValidatableResponse GetListOfUsersValidatableResponse();

    @Step("Getting List of users as string")
    String GetListOfUsersAsString();

    @Step("Check Response Status Code")
    void CheckResponseStatusCode(ValidatableResponse response, Integer statusCode);

    @Step("Check Response Time")
    void CheckResponseTime(ValidatableResponse response, Long milliSeconds);

    @Step("Get Response Data")
    JsonPath GetResponseJsonData(ValidatableResponse response);
}

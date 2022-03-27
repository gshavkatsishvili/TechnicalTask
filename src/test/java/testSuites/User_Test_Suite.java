package testSuites;

import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.implementations.UserService;
import services.interfaces.IUserService;

public class User_Test_Suite {
    IUserService userService = new UserService("https://gorest.co.in", "/public-api/users");
    ValidatableResponse responseBody = userService.GetListOfUsersValidatableResponse();

    @Test
    public void TC01_Check_If_HTTP_Response_Status_Code_Is_200() {
        userService.CheckResponseStatusCode(responseBody, 200);
    }

    @Test
    public void TC02_Check_If_ResponseTime_Is_Below_2500() {
        userService.CheckResponseTime(responseBody, 2500L);
    }

    @Test
    public void TC03_Check_If_Inner_Response_Code_Is_200() {
        JsonPath response = userService.getResponseJsonData(responseBody);
        Object statusCode = response.get("code");
        Assert.assertEquals(statusCode, 200, "Check if inner status code is equal to 200");
    }
}

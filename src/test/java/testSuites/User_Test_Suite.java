package testSuites;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.implementations.UserService;
import services.interfaces.IUserService;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

@Story("Users API Test Suite")
public class User_Test_Suite {
    IUserService userService = new UserService("https://gorest.co.in", "/public-api/users");
    ValidatableResponse responseBody = userService.GetListOfUsersValidatableResponse();

    @Test
    @Description("Check if Response Status Code is equal to 200")
    public void TC01_Check_If_HTTP_Response_Status_Code_Is_200() {
        userService.CheckResponseStatusCode(responseBody, 200);
    }

    @Test
    @Description("Check if Response Time is less than 2500 milliseconds")
    public void TC02_Check_If_ResponseTime_Is_Below_2500() {
        userService.CheckResponseTime(responseBody, 2500L);
    }

    @Test
    @Description("Check if inner response body's status code is equal to 200")
    public void TC03_Check_If_Inner_Response_Code_Is_200() {
        JsonPath response = userService.GetResponseJsonData(responseBody);
        Object statusCode = response.get("code");
        Assert.assertEquals(statusCode, 200, "Check if inner status code is equal to 200");
    }

    @Test()
    @Description("Check if API Response schema and JSON schema are same")
    public void TC04_Check_If_API_Response_And_Schema_Are_Equal() {
        File schema = new File(System.getProperty("user.dir") + "/src/main/resources/schema.JSON");
        responseBody.body(matchesJsonSchema(schema));
    }
}

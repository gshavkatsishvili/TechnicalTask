package testSuites;

import databaseLayer.implementations.CsvProcessor;
import databaseLayer.implementations.DatabaseProcessor;
import databaseLayer.interfaces.IDatabaseProcessor;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import services.implementations.UserService;
import services.interfaces.IUserService;

import java.io.IOException;
import java.sql.SQLException;

public class User_Data_Writer_Test_Suite {

    private final IUserService userService = new UserService("https://gorest.co.in", "/public-api/users");

    String responseBody = userService.GetListOfUsersAsString();
    CsvProcessor csvProcessor = new CsvProcessor();
    private final IDatabaseProcessor databaseProcessor = new DatabaseProcessor();

    public User_Data_Writer_Test_Suite() throws IOException {
    }

    @Test
    @Description("Retrieve API Data and add it into database")
    public void TC01_Insert_API_Data_Into_DataBase() {
        try {
            databaseProcessor.Insert_API_Response_Data_Into_Table(responseBody);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Description("Select data from database and print")
    public void TC02_Get_Data_From_DB_And_Print() throws Exception {
        databaseProcessor.Get_And_Print_Data_From_DB();
    }

    @Test
    @Description("Retrieve API Response Data and write it in CSV file")
    public void TC03_Write_API_Data_In_CSV_File() throws IOException {
        csvProcessor.Write_Data_In_CSV_File(responseBody);
    }
}

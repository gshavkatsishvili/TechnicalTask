package databaseLayer.implementations;

import databaseLayer.interfaces.IDatabaseProcessor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.*;

public abstract class DatabaseProcessor implements IDatabaseProcessor {

    public DatabaseProcessor(String connectionString, String user, String password) {
        _connectionString = connectionString;
        _user = user;
        _password = password;
    }

    private final String _connectionString; // = "jdbc:sqlserver://localhost;databaseName=Users";
    private final String _user; // = "admin";
    private final String _password; // = "Aa123456";

    public Connection ConnectToDB() throws SQLException {
        Connection connection = DriverManager.getConnection(_connectionString, _user, _password);
        System.out.println("Connection Successfully...");
        return connection;
    }

    public void Truncate_UsersTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("TRUNCATE TABLE UsersTable");
    }

    public void Insert_Data(Connection connection, JSONArray array) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO UsersTable values (?, ?, ?, ?, ?)");
        for (Object object : array) {
            JSONObject record = (JSONObject) object;
            long id = Long.parseLong(String.valueOf(record.get("id")));
            String name = (String) record.get("name");
            String email = (String) record.get("email");
            String gender = (String) record.get("gender");
            String status = (String) record.get("status");

            preparedStatement.setLong(1, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, status);
            preparedStatement.executeUpdate();
        }
        System.out.println("Data Inserted Successfully...");
    }

    public void Insert_API_Response_Data_Into_Table(String responseBody) throws SQLException {
        JSONParser jsonParser = new JSONParser();
        Connection con = ConnectToDB();
        try (con) {
            Truncate_UsersTable(con);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            Insert_Data(con, jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Get_And_Print_Data_From_DB() throws Exception {
        Connection connection = ConnectToDB();

        try (connection) {
            Statement selectData = connection.createStatement();
            ResultSet result = selectData.executeQuery("SELECT * FROM UsersTable");
            while (result.next()) {
                System.out.println(result.getInt("ID") + "|\t|" + result.getString("Name") + "|\t|" +
                        result.getString("Email") + "|\t|" + result.getString("Gender") +
                        "|\t|" + result.getString("Status") + "|");
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
    }
}

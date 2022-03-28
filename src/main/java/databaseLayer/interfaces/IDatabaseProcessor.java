package databaseLayer.interfaces;

import org.json.simple.JSONArray;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseProcessor {
    Connection ConnectToDB() throws SQLException, Exception;
    void Truncate_UsersTable(Connection connection) throws SQLException;
    void Insert_Data(Connection connection, JSONArray array) throws SQLException;
    void Insert_API_Response_Data_Into_Table(String responseBody) throws SQLException;
    void Get_And_Print_Data_From_DB() throws Exception;
}

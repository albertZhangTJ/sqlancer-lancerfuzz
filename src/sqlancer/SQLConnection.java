package sqlancer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection implements SQLancerDBConnection {

    private final Connection connection;
    private static int id = 0;
    private static int stmt_cnt = 0;

    public SQLConnection(Connection connection) {
        this.connection = connection;
        id++;
        System.out.println("SQLConnection created, id: "+id);
    }

    @Override
    public String getDatabaseVersion() throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        return meta.getDatabaseProductVersion();
    }

    @Override
    public void close() throws SQLException {
        System.out.println("Closing SQLConnection, id: "+id);
        try {
            connection.close();
            System.out.println("SQLConnection closed, id: "+id);
        }
        catch (Exception e){
            System.out.println("Error closing SQLConnection: " + e.getMessage());
            throw e;
        }
    }

    public Statement prepareStatement(String arg) throws SQLException {
        return connection.prepareStatement(arg);
    }

    public Statement createStatement() throws SQLException {
        stmt_cnt++;
        System.out.println("Statement created, id: "+stmt_cnt);
        return connection.createStatement();
    }

    public Statement createStatement(int arg1, int arg2) throws SQLException {
        return connection.createStatement(arg1, arg2);
    }
}

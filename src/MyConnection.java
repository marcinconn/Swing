import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class MyConnection {
    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;

    /**
     * Tworzy połączenie
     * @param command polecenie SQL do wykonania
     */
    public static void MakeConnection(String command){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("obiektowe");
        dataSource.setUser("root");
        dataSource.setPassword("");
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            stmt.execute(command);
            stmt.close();
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Sprawdza czy dany nick jest w bazie
     * @param command polecenie SQL do wykonania
     * @return zwraca true, jeśli jest
     */
    public static boolean isInDB(String command){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("obiektowe");
        dataSource.setUser("root");
        dataSource.setPassword("");
        boolean em=false;
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs=stmt.executeQuery(command);
            if(rs.next()==false) em=true;
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return em;
    }

    /**
     * Znajduje indeks danego usera w bazie
     * @param command polecenie SQL do wykonania
     * @return zwraca indeks danego usera
     */
    public static int findIndex(String command){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("obiektowe");
        dataSource.setUser("root");
        dataSource.setPassword("");
        Integer ind=-1;
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs=stmt.executeQuery(command);
            if(rs.next()) ind=rs.getInt("ID_U");
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ind;
    }

    /**
     * Tworzy wektor wektorów z danymi
     * @param command polecenie SQL do wykonania
     * @return wektor wektorów z danymi, które są wynikiem zapytania SQL
     */
    public static Vector showResults(String command){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("obiektowe");
        dataSource.setUser("root");
        dataSource.setPassword("");
        Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(command);
            ResultSetMetaData rsmd = rs.getMetaData();

            Vector<Vector<Object>> rows2 = new Vector<Vector<Object>>();
            int columnCount = rsmd.getColumnCount();
            while (rs.next())
            {
                Vector<Object> vector = new Vector<>();
                for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
                {
                    vector.add(rs.getObject(columnIndex));
                }
                rows2.add(vector);
            }
            rows=rows2;
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return rows;
    }

    /**
     * Sprawdza czy dany element jest w bazie
     * @param command polecenie SQL do wykonania
     * @return zwraca true jeśli jest w bazie
     */
    public static boolean isHere(String command){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("obiektowe");
        dataSource.setUser("root");
        dataSource.setPassword("");
        boolean idg=false;
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs=stmt.executeQuery(command);
            if(rs.next()==true) idg=true;
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return idg;
    }

    /**
     * Tworzy ArrayList, kiedy jest jedna wartość jako rezultat zapytania
     * @param command polecenie SQL do wykonania
     * @return zwraca ArrayList z wartością zwróconą
     */
    public static ArrayList<String> prepareEdit(String command){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("obiektowe");
        dataSource.setUser("root");
        dataSource.setPassword("");
        ArrayList<String> returnData = new ArrayList<>();
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(command);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next())
            {
                for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
                {
                    returnData.add(rs.getObject(columnIndex).toString());
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return returnData;
    }

    /**
     * Tworzy ArrayList, kiedy zwracane obiekty są różnych typów
     * @param command polecenie SQL do wykonania
     * @return zwraca ArrayList z danymi
     */
    public static ArrayList<Object> prepareEditforGrades(String command){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("obiektowe");
        dataSource.setUser("root");
        dataSource.setPassword("");
        ArrayList<Object> returnData = new ArrayList<>();
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(command);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next())
            {
                for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
                {
                    returnData.add(rs.getObject(columnIndex));
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return returnData;
    }

    /**
     * Aktualizuje dane w bazie
     * @param command polecenie SQL do wykonania
     */
    public static void updateDB(String command){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("obiektowe");
        dataSource.setUser("root");
        dataSource.setPassword("");
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(command);
            stmt.close();
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}

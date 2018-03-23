import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuUserDao extends UserDao {

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //mysql driver load
        Class.forName("com.mysql.jdbc.Driver");
        //Connection 맺고
        return DriverManager.getConnection("jdbc:mysql://localhost/ojlee?characterEncoding=utf-8"
                , "root", "amdapu");

    }
}

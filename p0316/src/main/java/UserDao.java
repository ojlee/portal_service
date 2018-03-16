import java.sql.*;


public class UserDao {
    public User get(int id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();

        //sql 작성하고
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from User where id = ?");
        preparedStatement.setInt(1, id);
        //sql 실행하고
        ResultSet resultSet = preparedStatement.executeQuery();
        //결과를 User 에 매핑하고
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원을 해지하고
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과를 리턴한다.
        return user;
    }

    public Integer insert(User user) throws SQLException, ClassNotFoundException {
        //mysql driver load
        Connection connection = getConnection();
        //sql 작성하고
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into User(name, password) VALUES  (?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());


        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("SELECT last_insert_id()");

        //sql 실행하고
        ResultSet resultSet = preparedStatement.executeQuery();
        //결과를 User 에 매핑하고
        resultSet.next();

        Integer id = resultSet.getInt(1);

        //자원을 해지하고
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과를 리턴한다.

        return id;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        //mysql driver load
        Class.forName("com.mysql.jdbc.Driver");
        //Connection 맺고
        return DriverManager.getConnection("jdbc:mysql://localhost/ojlee?characterEncoding=utf-8"
                , "root", "amdapu");
    }

}
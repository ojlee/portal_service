import java.sql.*;


public class UserDao {
    private final ConnectionMaker connectionMaker;// 한라대 디펜던시 문제 심함

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(int id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();

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
        Connection connection = connectionMaker.getConnection();
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

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return connectionMaker.getConnection();
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

public class JejuConnectionMaker implements ConnectionMaker {

    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //mysql driver load
        Class.forName(className);
        //Connection 맺고
        return DriverManager.getConnection(url,username,password);
        //아이디 패스워드 밸류들 환경변수로 두어 시스템마다 관리, 디비 커넥션 정보도 관리할 수 있고 , 아이디 패스워드가 드러나는 것도 방지
    }
}

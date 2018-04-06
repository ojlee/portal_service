//DI 의존성 주입
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }
    @Bean
    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
    // 얘를 스프링으로 바꿔주기 위해서 gradle 만듬 그래들은 의존성을 관리하고 빌드까지 해주는 역할
    // build.gradle 관리해 주면 의존성 라이브러리 알아서 받아줌
    //
}

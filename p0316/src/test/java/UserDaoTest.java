import static org.hamcrest.MatcherAssert.*; // 이 안의 static 메소드들을 자유롭게 이용가능
import static org.hamcrest.CoreMatchers.*;

import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;


public class UserDaoTest {

    private UserDao userDao;
    private DaoFactory daoFactory;

    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName("hulk");
        user.setPassword("1111");
        Integer id =  userDao.insert(user);

        user.setId(id);
        user.setName("허윤호");
        user.setPassword("1234");
        userDao.update(user);

        User updatedUser = userDao.get(id);
        assertThat(updatedUser.getId(), is(user.getId()));
        assertThat(updatedUser.getName(), is(user.getName()));
        assertThat(updatedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = new User();
        Integer id = insertUserTest(user);

        userDao.delete(id);

        User deletedUser = userDao.get(id);
        assertThat(deletedUser, nullValue());

    }

    private Integer insertUserTest(User user) throws SQLException, ClassNotFoundException {
        user.setName("헐크");
        user.setPassword("1111");
        return userDao.insert(user);
    }


    @Test //실행
    public void get() throws SQLException, ClassNotFoundException{ // 예외처리를 잘 몰라 알아서 하게끔 throw 시킴
        int id = 1;
        User user = userDao.get(id);
        assertThat(user.getId(), is(1));
        assertThat(user.getName(), is("이오주"));
        assertThat(user.getPassword(), is("1234"));
    }
    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName("hulk");
        user.setPassword("1111");
        Integer id =  userDao.insert(user);

        User insertedUser = userDao.get(id);
        assertThat(insertedUser.getId(), is(id));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

}

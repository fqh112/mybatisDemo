package model;

import com.gh.dao.UserDao;
import com.gh.entity.Login;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class TestMybatis {

    Logger logger = Logger.getLogger(TestMybatis.class);

    private SqlSessionFactory factory = null;

    @Before
    public void init() {
        try {
            //手动加载核心配置文件
            Reader resource = Resources.getResourceAsReader("sqlMapConfig.xml");
            factory = new SqlSessionFactoryBuilder().build(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void se() {
        SqlSession session = factory.openSession();
        UserDao dao = session.getMapper(UserDao.class);
        try {
            List<Login> list = dao.findAll();
            logger.debug(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        SqlSession session = factory.openSession();
        UserDao dao = session.getMapper(UserDao.class);
        Login login = new Login();
        login.setLogin_name("sss");
        login.setPassword("sss");
        login.setPhone("15394280152");
        try {
            dao.insertUser(login);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        SqlSession session = factory.openSession();
        //获取接口的代理对象
        UserDao dao = session.getMapper(UserDao.class);
        try {
            dao.deleteById(112);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        SqlSession session = factory.openSession();
        UserDao dao = session.getMapper(UserDao.class);
        try {
            List<Login> list = dao.selectByName("o");
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

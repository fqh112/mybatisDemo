package model;

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

public class TestMybits {

    Logger logger = Logger.getLogger(TestMybits.class);

    private SqlSessionFactory factory = null;

    @Before
    public void before() {
        //1.手动加载核心配置文件
        Reader resourceAsReader = null;
        try {
            resourceAsReader = Resources.getResourceAsReader("sqlMapConfig.xml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //2.创建一个sessionFactory
        factory = new SqlSessionFactoryBuilder().build(resourceAsReader);


    }

    @Test
    public void findAll() {
        //3.获取一个session对象
        SqlSession session = factory.openSession();
        //4.操作user.xml
        List<Login> list = session.selectList("login.user");
        System.out.println(list);
    }

    @Test
    public void findById() {
        SqlSession session = factory.openSession();
        Login use = session.selectOne("login.findLoginById", 109);
        System.out.println(use);
    }

    @Test
    public void findLoginByUsername() {
        SqlSession session = factory.openSession();
        List<Login> list = session.selectList("login.findLoginByUsername", "o");
        System.out.println(list);
    }

    @Test
    public void insertUser() {
        SqlSession session = factory.openSession();
        Login login = new Login();
        login.setLogin_name("方启豪");
        login.setPassword("123456");
        login.setPhone("1562323");
        int count = session.insert("login.insertUser", login);
        System.out.println(count);
        session.commit();
    }

    @Test
    public void deleteById() {
        SqlSession sqlSession = factory.openSession();
        int count = sqlSession.delete("login.deleteById", 111);
        System.out.println(count);
        sqlSession.commit();
    }

    @Test
    public void updateById() {
        SqlSession sqlSession = factory.openSession();
        Login login = new Login();
        login.setLogin_id(108);
        login.setLogin_name("哈哈");
        login.setPhone("13");
        int update = sqlSession.update("login.updateById", login);
        System.out.println(update);
        sqlSession.rollback();
    }

    @Test
    public void print() {
        Logger logger = Logger.getLogger(TestMybits.class);
        logger.debug("这是一个debug日志");
        logger.warn("这是一个warn日志");
        logger.error("这是一个error日志");
        logger.info("这是一个info日志");
    }

}

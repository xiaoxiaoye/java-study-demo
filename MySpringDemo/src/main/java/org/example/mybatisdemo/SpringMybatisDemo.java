package org.example.mybatisdemo;


import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Configuration
@Transactional
public class SpringMybatisDemo {

    @Autowired
    UserMapper userMapper;

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(SpringMybatisDemo.class);
        applicationContext.refresh();

//        DataSource dataSource = applicationContext.getBean("dataSource",DataSource.class);
//        System.out.println(dataSource.getConnection());


//        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
//        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM user;");
//        System.out.println(list);

        SpringMybatisDemo springMybatisDemo = applicationContext.getBean(SpringMybatisDemo.class);
        User user = springMybatisDemo.getUser(1);
        System.out.println(user);

        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        User user1 = userMapper.getUserInfo(1);
        System.out.println(user1);

        applicationContext.close();
    }

    public User getUser(int id) {
        User user = userMapper.getUser(1);
        return user;
    }

    @Bean
    public static SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        Resource resource = new ClassPathResource("UserMapper.xml");
        factoryBean.setMapperLocations(resource);
        SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
//        sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
        return sqlSessionFactory;
    }

    @Bean
    public static DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/vos");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("vos");
        return dataSource;
    }

    @Bean
    public static UserMapper userMapper(SqlSessionFactory sessionFactory) throws Exception {
        sessionFactory.getConfiguration().addMapper(User.class);
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate.getMapper(UserMapper.class);
    }

    @Bean
    public static JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}

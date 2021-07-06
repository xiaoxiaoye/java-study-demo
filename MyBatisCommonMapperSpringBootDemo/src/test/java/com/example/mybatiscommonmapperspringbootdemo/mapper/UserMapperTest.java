package com.example.mybatiscommonmapperspringbootdemo.mapper;

import com.example.mybatiscommonmapperspringbootdemo.MybatisCommonMapperSpringBootDemoApplicationTests;
import com.example.mybatiscommonmapperspringbootdemo.mybatis.entity.User;
import com.example.mybatiscommonmapperspringbootdemo.mybatis.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
public class UserMapperTest extends MybatisCommonMapperSpringBootDemoApplicationTests {
    public final static Logger log = LoggerFactory.getLogger("com.example.mybatiscommonmapperspringbootdemo.mapper.UserMapperTest");

    @Autowired
    UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setId(20);
        user.setAddress("hangzhou");
        user.setName("yy");
        userMapper.insert(user);

        Assert.assertNotNull(user.getId());

        log.debug("user: {}", user);
    }

    @Test
    public void InsertUserList() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("user_" + i);
            user.setAddress("address_" + i);
            list.add(user);
        }

        int i = userMapper.insertList(list);
        Assert.assertEquals(i, list.size());

        List<Integer> ids = list.stream().map(User::getId).collect(Collectors.toList());
        log.debug("【测试主键回写#userList.ids】= {}", ids);
    }

    @Test
    public void testDelete() {
        initData();

        int i = userMapper.deleteByPrimaryKey(1);
        Assert.assertEquals(1, i);

        User user = userMapper.selectByPrimaryKey(1);
        Assert.assertNull(user);
    }

    @Test
    public void testQueryByPageAndSort() {
        initData();

        int currentPage = 1;
        int pageSize = 5;
        String orderBy = "id desc";
        int count = userMapper.selectCount(null);
        PageHelper.startPage(currentPage, pageSize, orderBy);
        List<User> users = userMapper.selectAll();
        users.stream().forEach(System.out::println);

        PageInfo<User> userPageInfo = new PageInfo<>(users);
        Assert.assertEquals(5, userPageInfo.getSize());
        Assert.assertEquals(count, userPageInfo.getTotal());
        log.debug("【userPageInfo】= {}", userPageInfo);

        currentPage = userPageInfo.getNextPage();
        PageHelper.startPage(currentPage, pageSize, orderBy);
        users = userMapper.selectAll();
        users.stream().forEach(System.out::println);
    }


    private void initData() {
        InsertUserList();
    }

}

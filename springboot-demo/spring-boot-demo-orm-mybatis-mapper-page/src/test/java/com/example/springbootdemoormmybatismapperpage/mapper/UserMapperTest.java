package com.example.springbootdemoormmybatismapperpage.mapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.core.date.DateTime;
import com.example.springbootdemoormmybatismapperpage.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        String salt = IdUtil.fastSimpleUUID();
        User testSave3 = User.builder().name("testSave3")
                .password(SecureUtil.md5("123456" + salt))
                .salt(salt).email("testSave3@xkcoding.com")
                .phoneNumber("17300000003")
                .status(1)
                .lastLoginTime(new DateTime())
                .createTime(new DateTime())
                .lastUpdateTime(new DateTime())
                .build();
        userMapper.insertUseGeneratedKeys(testSave3);
        Assertions.assertNotNull(testSave3.getId());
        log.debug("【测试主键回写#testSave3.getId()】= {}", testSave3.getId());
    }

    @Test
    public void testInsertList() {
        List<User> userList = Lists.newArrayList();
        for (int i = 4; i < 14; i++) {
            String salt = IdUtil.fastSimpleUUID();
            User user = User.builder().name("testSave" + i)
                    .password(SecureUtil.md5("123456" + salt))
                    .salt(salt).email("testSave" + i + "@xkcoding.com")
                    .phoneNumber("1730000000" + i)
                    .status(1)
                    .lastLoginTime(new DateTime())
                    .createTime(new DateTime())
                    .lastUpdateTime(new DateTime())
                    .build();
            userList.add(user);
        }
        int i = userMapper.insertList(userList);
        Assertions.assertEquals(userList.size(), i);
        List<Long> ids = userList.stream().map(User::getId).collect(Collectors.toList());
        log.debug("【测试主键回写#userList.ids】= {}", ids);
    }

    @Test
    public void testDelete() {
        Long primaryKey = 1L;
        int i = userMapper.deleteByPrimaryKey(primaryKey);
        Assertions.assertEquals(1, i);
        User user = userMapper.selectByPrimaryKey(primaryKey);
        Assertions.assertNull(user);
    }

    @Test
    public void testUpdate() {
        Long primaryKey = 1L;
        User user = userMapper.selectByPrimaryKey(primaryKey);
        user.setName("通用Mapper名字更新");
        int i = userMapper.updateByPrimaryKeySelective(user);
        Assertions.assertEquals(1, i);
        User update = userMapper.selectByPrimaryKey(primaryKey);
        Assertions.assertNotNull(update);
        Assertions.assertEquals("通用Mapper名字更新", update.getName());
        log.debug("【update】= {}", update);
    }

    @Test
    public void testQueryOne() {
        User user = userMapper.selectByPrimaryKey(1L);
        Assertions.assertNotNull(user);
        log.debug("[user] = {}", user);
    }

    @Test
    public void testQueryAll() {
        List<User> users = userMapper.selectAll();
        Assertions.assertTrue(CollUtil.isNotEmpty(users));
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
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        Assertions.assertEquals(5, userPageInfo.getSize());
        Assertions.assertEquals(count, userPageInfo.getTotal());
    }

    @Test
    public void testQueryByCondition() {
        initData();
        Example example = new Example(User.class);
        example.createCriteria().andLike("name", "%Save1%").orEqualTo("phoneNumber", "17300000001");
        example.setOrderByClause("id desc");
        int count = userMapper.selectCountByExample(example);

        PageHelper.startPage(1, 3);
        List<User> userList = userMapper.selectByExample(example);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);

        Assertions.assertEquals(userList.size(), userPageInfo.getSize());
        Assertions.assertEquals(count, userPageInfo.getTotal());
    }

    private void initData() {
        testInsertList();
    }
}

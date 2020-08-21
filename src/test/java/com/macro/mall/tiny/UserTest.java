package com.macro.mall.tiny;
/*
  内置 CRUD 演示

*/


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.entity.User;
import com.macro.mall.tiny.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void aTest() {
        User user = new User();
        user.setName("萧山1");
        user.setAge(3);
        user.setEmail("abc@mp.com");

        assertThat(userMapper.insert(user)).isGreaterThan(0);
        // 成功直接拿会写的 ID
        assertThat(user.getId()).isNotNull();

    }

    @Test
    public void bDelete() {
        assertThat(userMapper.deleteById(1296680641052168193L)).isGreaterThan(0);
        assertThat(userMapper.delete(new QueryWrapper<User>().lambda().eq(User::getName, "Jack"))).isGreaterThan(0);


    }

    @Test
    public void cUpdate() {
        assertThat(userMapper.updateById(new User().setId(5L).setEmail("ab@c.c"))).isGreaterThan(0);
        assertThat(
                userMapper.update(
                        new User().setName("mp"),
                        Wrappers.<User>lambdaUpdate()
                                .set(User::getAge, 20)
                                .eq(User::getId, 4)
                )
        ).isGreaterThan(0);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 3);
        User user  = new User();
        user.setEmail("nw email@sina.com");
        assertThat(userMapper.update(user, queryWrapper)).isGreaterThan(0);
    }

    @Test
    public void dSelect() {
        userMapper.insert(new User().setId(10086l).setName("miemie").setEmail("miemie@baomidou.com").setAge(3));
        assertThat(userMapper.selectById(10086l).getEmail()).isEqualTo("miemie@baomidou.com");
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 10086l));
        assertThat(user.getName()).isEqualTo("miemie");

        userMapper.selectList(Wrappers.<User>lambdaQuery().select(User::getId)
        ).forEach(x -> {
            assertThat(x.getId()).isNotNull();
            assertThat(x.getName()).isNotNull();
        });
        userMapper.selectList(new QueryWrapper<User>().select("id", "name")).forEach(x -> {
            assertThat(x.getName()).isNotNull();
            assertThat(x.getAge()).isNotNull();
        });
        userMapper.selectList(Wrappers.lambdaQuery(new User().setId(10086L)));

        ArrayList<Long> list = new ArrayList<>();
        list.add(10086L);
        userMapper.selectList(Wrappers.<User>lambdaQuery().in(User::getId, list));
    }

    @Test
    public void orderBy() {
        List<User> users = userMapper.selectList(Wrappers.<User>query().orderByAsc("age"));
        assertThat(users).isNotEmpty();
    }

    @Test
    public void selectMap() {
        List<Map<String, Object>> mapList = userMapper.selectMaps(Wrappers.<User>query().orderByAsc("age"));
        assertThat(mapList).isNotEmpty();
        assertThat(mapList.get(0)).isNotEmpty();
        System.out.println(mapList.get(0));
    }

    @Test
    public void selectMapPage() {
        IPage<Map<String, Object>> page = userMapper.selectMapsPage(new Page<>(1, 5), Wrappers.<User>query().orderByAsc("age"));
        assertThat(page).isNotNull();
        assertThat(page.getRecords()).isNotEmpty();
        assertThat(page.getRecords().get(0)).isNotEmpty();
        System.out.println(page.getRecords().get(0));
    }

    @Test
    public void orderByLambda() {
        List<User> users = userMapper.selectList(Wrappers.<User>lambdaQuery().orderByAsc(User::getAge));
        assertThat(users).isNotEmpty();
    }

    @Test
    public void testSelectMaxId() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        User user = userMapper.selectOne(wrapper);
        System.out.println("maxId=" + user.getId());
        List<User> users = userMapper.selectList(Wrappers.<User>lambdaQuery().orderByDesc(User::getId));
        Assert.assertEquals(user.getId().longValue(), users.get(0).getId().longValue());
    }

    @Test
    public void testGroup() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("age, count(*)").groupBy("age");
        List<Map<String, Object>> mapList = userMapper.selectMaps(wrapper);
        for (Map<String, Object> mp : mapList) {
            System.out.println(mp);
        }

        /**
         * lambdaQueryWrapper groupBy orderBy
         */
        LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda().select(User::getAge).groupBy(User::getAge).orderByAsc(User::getAge);
        for (User user : userMapper.selectList(lambdaQueryWrapper)) {
            System.out.println(user);
        }


    }

    @Test
    public void testTableFieldExistFalse() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("age, count(age) as count").groupBy("age");
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
        list.forEach(x -> {
            Assert.assertNull(x.getId());
        });
        userMapper.insert(
                new User().setId(10088L).setAge(12).setName("miemie").setEmail("miemie@baomidou.com")
        );
        User miemie = userMapper.selectById(10088L);
        Assert.assertNotNull(miemie);
    }
}

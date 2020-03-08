package com.example.controller;

import com.example.KVEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Controller
@RequestMapping("list")
public class ListController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ResponseBody
    @RequestMapping("/add")
    public List<String> add(@RequestBody List<String> list){
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        for (String string : list) {
            listOperations.leftPushAll("redis_list",list);
        }

//        listOperations.rightPush("redis_list",string);
        Long size = listOperations.size("redis_list"); // list如果不存在,返回0
        List<String> all = listOperations.range("redis_list", 0, -1); // 查询全部
        List<String> redis_list = listOperations.range("redis_list", 0, 2); // 查询前2个元素

        return list();
    }
    @ResponseBody
    @RequestMapping("/list")
    public List<String> list(){
        return getList();
    }
    // 获取list的方法
    public List<String> getList(){
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        List<String> redis_list = listOperations.range("redis_list", 0, -1);
        listOperations.size("redis_list"); // size
//        List<String> redis_list = listOperations.range("redis_list", 0, 2); // 查询前2个

        Stream.of(redis_list).forEach(key-> System.out.println(key));
        return redis_list;
    }
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestBody String string){
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        //(1:从左往右 -1:从右往左 0:删除全部)
        listOperations.remove("redis_list",0,string); // 第三个参数是删除的对象



        return "delete";

    }
}


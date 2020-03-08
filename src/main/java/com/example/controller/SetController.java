package com.example.controller;

import com.example.KVEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("set")
public class SetController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @ResponseBody
    @RequestMapping("/list")
    public Set<String> list(){
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        Set<String> keys = stringRedisTemplate.keys("*"); // keys * 查看所有
        for (String key:
                keys) {
            System.out.println(keys);
        }
        return keys;
    }

    @ResponseBody
    @RequestMapping("/add")
    public Set<String> add(@RequestBody KVEntity kvEntity){
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();

        valueOperations.set(kvEntity.getKey(),kvEntity.getValue()); // 新增



        Set<String> keys = stringRedisTemplate.keys("*"); // keys * 查看所有

        for (String key:
                keys) {
            System.out.println(keys);
        }
        return keys;


    }
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(String string){
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        stringRedisTemplate.delete(string); // 删除

        Set<String> keys = stringRedisTemplate.keys("*"); // keys * 查看所有
        for (String key:
                keys) {
            System.out.println(keys);
        }

        return "delete";

    }
}

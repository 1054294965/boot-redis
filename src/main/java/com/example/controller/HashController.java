package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("hash")
public class HashController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @ResponseBody
    @RequestMapping("/add")
    public Map<String,String> add(){
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        hashOperations.put("user_hash","zhangfei","black face"); // put相当于添加
        hashOperations.put("user_hash","guanyu","red face");
        return getAll();
    }
    @ResponseBody
    @RequestMapping("/update")
    public Map<String,String> update(){
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        Boolean aBoolean = hashOperations.hasKey("user_hash", "zhangfei"); // 是否存在
        System.out.println(aBoolean);
        hashOperations.put("user_hash","zhangfei","mangfu"); //put 会重置，相当于update
        return getAll();
    }
    @ResponseBody
    @RequestMapping("/all")
    public Map<String,String> all(){
        return getAll();
    }
    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,String> delete(){
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        Long delete = hashOperations.delete("user_hash", "zhangfei3");
        return getAll();
    }
    public Map<String,String> getAll(){
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        Map<String, String> userMap = hashOperations.entries("user_hash"); // entries
        Set<String> userKeys = hashOperations.keys("user_hash");// keys
        List<String> userValues = hashOperations.values("user_hash"); // values
        return userMap;
    }
}

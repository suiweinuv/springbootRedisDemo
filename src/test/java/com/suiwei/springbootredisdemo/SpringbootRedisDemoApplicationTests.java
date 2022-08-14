package com.suiwei.springbootredisdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suiwei.springbootredisdemo.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class SpringbootRedisDemoApplicationTests {
    //默认使用jdk序列化
    //@Autowired
   // private  RedisTemplate redisTemplate;

    //自定义序列化
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //ObjectMapper可以帮助我们快速的进行各个类型和Json类型的相互转换。跟fastjson，Jackson一样
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testString() {
        redisTemplate.opsForValue().set("key03","suiwei03");
        Object result = redisTemplate.opsForValue().get("key03");
        System.out.println(result);
    }
    //自动完成序列化与反序列化
    @Test
    void testSaveUser(){
        redisTemplate.opsForValue().set("user:100",new User("suiwei",13));
        User user = (User)redisTemplate.opsForValue().get("user:100");
        System.out.println(user);
    }

    @Test
    void  String() throws JsonProcessingException {
        User user = new User("s1",18);
        //手动序列化
        String json = mapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user:200",json);
        String result= stringRedisTemplate.opsForValue().get("user:200");
        //手动反序列化
        User user1 = mapper.readValue(result,User.class);
        System.out.println(user1);

    }
    @Test
    void  testHash(){
        stringRedisTemplate.opsForHash().put("user:300","name","w1");
        stringRedisTemplate.opsForHash().put("user:300","age","15");
        Map<Object,Object> map = stringRedisTemplate.opsForHash().entries("user:300");
        System.out.println(map);
    }

}

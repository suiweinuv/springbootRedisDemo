# 工程简介
springboot 整合redis，使用SpringDataRedis客户端

1.pom文件引入redis依赖、引入连接池依赖
2.配置redis的地址信息以及连接池信息（默认使用lettuce，如果用jedis需要pom引入依赖）
    虽然默认是lettuce，但是必须在配置文件中指定pool的参数才能生效
3.注入redisTemplate


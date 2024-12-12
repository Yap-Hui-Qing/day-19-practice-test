package vttp.batchb.ssf.day19_practice_test.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {
    
    @Autowired
    @Qualifier("redis-0")
    RedisTemplate<String, String> redisTemplate;

    public void saveTask(Map<String, String> map){
        redisTemplate.opsForHash().putAll("tasks", map);
    }

    // hvals tasks
    public List<Object> getTasks(String key){
        return redisTemplate.opsForHash().values(key);
    }
}

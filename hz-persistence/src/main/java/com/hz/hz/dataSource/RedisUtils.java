
package com.hz.hz.dataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class RedisUtils {


    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);
//    @Resource(name = "redisTemplate")
    private RedisTemplate<String,String> redisTemplate;


    private RedisUtils() {}

    private static RedisUtils redisUtils = null;
    @PostConstruct
    public void init() {
        if (redisUtils ==null){
            redisUtils = this;

            String ping = redisTemplate.getConnectionFactory().getConnection().ping();
            logger.error("redis ping = "+ping);
        }


    }

    public static boolean del(String key) {
        redisUtils.redisTemplate.delete(key);
        return true;
    }

    public static  void  set(String key,String value,long timeout){
        redisUtils.redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.SECONDS);
    }
    public static  void  set(String key,String value){
        redisUtils.redisTemplate.opsForValue().set(key,value);
    }
    public static  String  get(String key){

       return redisUtils.redisTemplate.opsForValue().get(key);

    }
    public static  String  getString(String key){

        return  new String(get(key));

    }
    public static void expire(String key, long timeout) {
        redisUtils.redisTemplate.expire(key, Long.valueOf(timeout),TimeUnit.SECONDS);
    }
    public static long increment(String key,long step) {
       return  redisUtils.redisTemplate.opsForValue().increment(key, step);
    }
    /**
     * 缓存List数据
     * @param key        缓存的键值
     * @param dataList    待缓存的List数据
     * @return            缓存的对象
     */
    public ListOperations<String, String> setCacheList(String key, List<String> dataList)
    {
        ListOperations<String, String> listOperation = redisUtils.redisTemplate.opsForList();
        if(null != dataList)
        {
            int size = dataList.size();
            for(int i = 0; i < size ; i ++)
            {
                listOperation.rightPush(key,dataList.get(i));
            }
        }
        return listOperation;
    }
    /**
     * 获得缓存的list对象
     * @param key    缓存的键值
     * @return        缓存键值对应的数据
     */
    public List<String> getCacheList(String key)
    {
        List<String> dataList = new ArrayList<String>();
        ListOperations<String, String> listOperation = redisUtils.redisTemplate.opsForList();
        Long size = listOperation.size(key);

        for(int i = 0 ; i < size ; i ++)
        {
            dataList.add(listOperation.leftPop(key));
        }
        return dataList;
    }

    /**
     * 获得缓存的list对象
     * @Title: range
     * @Description:
     * @param @param key
     * @param @param start
     * @param @param end
     * @param @return
     * @return
     * @throws
     */
    public List<String> rangeList(String key, long start, long end) {
        ListOperations<String, String> listOperation = redisUtils.redisTemplate.opsForList();
        return listOperation.range(key, start, end);
    }

    /**
     * list集合长度
     * @param key
     * @return
     */
    public Long listSize(String key) {
        return redisUtils.redisTemplate.opsForList().size(key);
    }

    /**
     * 覆盖操作,将覆盖List中指定位置的值
     * @param key
     * @param  index 位置
     * @param  value
     * @return 状态码
     * */
    public void listSet(String key, int index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }


    /**
     * 向List尾部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     * */
    public static  void  leftPush(String key,String value){
        redisUtils.redisTemplate.opsForList().leftPush(key,value);
    }

    /**
     * 尾部推出
     * @param key
     */
    public static  void  leftPop(String key){
        redisUtils.redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 向List头部追加记录
     *
     * @param key
     *
     * @param value
     *
     * @return 记录总数
     * */
    public static  void  rightPush(String key,String value){
        redisUtils.redisTemplate.opsForList().rightPush(key,value);
    }

    /**
     *
     * @param key
     */
    public static  void  rightPop(String key){

        redisUtils.redisTemplate.opsForList().rightPop(key);
    }

    /**
     *
     * @param key
     */
    public static  void  size(String key){
        redisUtils.redisTemplate.opsForList().size(key);
    }


    /**
     *
     * @param key
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public static List<String> range(String key, long beginIndex, long endIndex){

      return redisUtils.redisTemplate.opsForList().range(key,beginIndex,endIndex);

    }


    /**
     * 缓存Map
     * @param key
     * @param dataMap
     * @return
     */
    public int setCacheMap(String key,Map<String, String> dataMap)
    {
        if(null != dataMap)
        {
            HashOperations<String, Object, Object> hashOperations = redisUtils.redisTemplate.opsForHash();
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                if(hashOperations != null){
                    hashOperations.put(key,entry.getKey(),entry.getValue());
                } else{
                    return 0;
                }
            }
        } else{
            return 0;
        }
        return dataMap.size();
    }

    /**
     * 获得缓存的Map
     * @param key
     * @return
     */
    public Map<Object, Object> getCacheMap(String key)
    {
        Map<Object, Object> map = redisUtils.redisTemplate.opsForHash().entries(key);
        return map;
    }

    /**
     * 缓存Map
     * @param key
     * @param dataMap
     * @return
     */
    public void setCacheIntegerMap(String key,Map<String, String> dataMap)
    {
        HashOperations<String, String, String> hashOperations = redisUtils.redisTemplate.opsForHash();
        if(null != dataMap)
        {
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {

                hashOperations.put(key,entry.getKey(),entry.getValue());
            }

        }
    }

    /**
     * 获得缓存的Map
     * @param key
     * @return
     */
    public Map<Object, Object> getCacheIntegerMap(String key)
    {
        Map<Object, Object> map = redisUtils.redisTemplate.opsForHash().entries(key);
        /*Map<String, T> map = hashOperation.entries(key);*/
        return map;
    }

    /**
     * 是否存在
     * @param key
     * @return
     */
    public static  boolean  exists(String key){

        return redisUtils.redisTemplate.hasKey(key);

    }
}



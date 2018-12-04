package com.dedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/5/27.
 */
public class JedisTest {

    @Test
    public void testJedis() throws Exception {

        // 创建链接对象  参数：host post
        Jedis jedis = new Jedis("192.168.198.128", 6379);
        // 直接使用jedis 操作redis
        jedis.auth("123456");
        jedis.set("testqinls", "qinls");

        System.out.println(jedis.get("testqinls"));
        // 关闭链接
        jedis.close();

    }

    /**
     * 连接池测试
     *
     * @throws Exception
     */
    @Test
    public void testJedisPool() throws Exception {
        // 创建连接池对象 两个参数
        JedisPool jedisPool = new JedisPool("192.168.198.128", 6379);
        // 从连接池获得一个连接
        Jedis jedis = jedisPool.getResource();
        jedis.auth("123456");

        // 使用jedis操作redis
        String string = jedis.get("testqinls");
        System.out.println(string);

        // 关闭连接  连接池回收
        jedis.close();
        // 关闭连接池
        jedisPool.close();
    }

    /**
     * 集群连接方式
     */

    @Test
    public void testJedisCluster() throws Exception {
        // 创建JedisCluster 对象  只有一个参数 node 是set 类型。set 中包含很多个HostAndProt对象
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.198.128", 7001));
        nodes.add(new HostAndPort("192.168.198.128", 7002));
        nodes.add(new HostAndPort("192.168.198.128", 7003));
        nodes.add(new HostAndPort("192.168.198.128", 7004));
        nodes.add(new HostAndPort("192.168.198.128", 7005));
        nodes.add(new HostAndPort("192.168.198.128", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);

        jedisCluster.set("test01", "12301");
        String str = jedisCluster.get("test01");
        System.out.println(str);
        jedisCluster.close();


    }

}

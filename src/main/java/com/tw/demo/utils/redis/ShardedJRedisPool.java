package com.tw.demo.utils.redis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.util.Hashing;

public class ShardedJRedisPool {

	private JedisCommands jedis;
	private ShardedJRedis shardedJRedis;

	public ShardedJRedisPool(final GenericObjectPoolConfig poolConfig, List<JedisShardInfo> shardInfos) {
		this(poolConfig, shardInfos, Hashing.MURMUR_HASH);
	}

	public ShardedJRedisPool(final GenericObjectPoolConfig poolConfig, List<JedisShardInfo> shardInfos, Hashing algo) {
		this(poolConfig, shardInfos, Hashing.MURMUR_HASH, null);
	}

	public ShardedJRedisPool(final GenericObjectPoolConfig poolConfig, List<JedisShardInfo> shardInfos, List<JedisShardInfo> infos1, Pattern keyTagPattern) {
		this(poolConfig, shardInfos, Hashing.MURMUR_HASH, keyTagPattern);
	}

	public ShardedJRedisPool(final GenericObjectPoolConfig poolConfig, List<JedisShardInfo> shardInfos, Hashing algo, Pattern keyTagPattern) {
		shardedJRedis = new ShardedJRedis(poolConfig, shardInfos, algo, keyTagPattern);
		jedis = (JedisCommands) Proxy.newProxyInstance(JedisCommands.class.getClassLoader(), new Class[] { JedisCommands.class }, new RedisGroupInvocationHandler());

	}

	public JedisCommands getResource() {
		return jedis;
	}



	public class RedisGroupInvocationHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return shardedJRedis.invoke(method, args);
		}
	}

	public void returnBrokenResource(JedisCommands jedis) {
		// TODO return the broken resource
	}

	public void returnResource(JedisCommands jedis) {
		// TODO return the resource
	}

	public void destroy() {
		// TODO destroy the pool
	}
}

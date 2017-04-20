package com.tw.demo.utils.redis.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tw.demo.utils.SystemConfiguration;
import com.tw.demo.utils.redis.ShardedJRedisPool;

import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

public class RedisClient {
	private static ShardedJRedisPool pool;
	private static SystemConfiguration sc = SystemConfiguration.getInstance();
	private static Logger logger = Logger.getLogger(RedisClient.class);

	static {
		init();
	}

	public static void init() {
		int maxIdle = sc.getInt("redis.pool.maxIdle");
		int maxTotal = sc.getInt("reids.pool.maxTotal");
		int maxWaitMillis = sc.getInt("redis.pool.maxWaitMillis");
		int socketTimeout = sc.getInt("redis.socket.timeout");
		String servers = sc.getString("redis.server.list");

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(maxIdle);
		config.setMaxTotal(maxTotal);
		config.setBlockWhenExhausted(false);
		config.setMaxWaitMillis(maxWaitMillis);
		List<JedisShardInfo> infos = new ArrayList<JedisShardInfo>();

		String[] hosts = servers.split(";");
		for (String host : hosts) {
			String[] datas = host.split(":");
			infos.add(new JedisShardInfo(datas[0], Integer.valueOf(datas[1]), socketTimeout));
		}

		logger.info("redis config maxIdle:" + maxIdle + " maxTotal:" + maxTotal + " maxWaitMillis:" + maxWaitMillis + "socketTimeout:"
				+ socketTimeout + " host infos:" + infos);
		pool = new ShardedJRedisPool(config, infos);
	}

	public static void main(String[] args) {
		init();
	}

	public static JedisCommands getJedisCommands() {
		return pool.getResource();
	}
}

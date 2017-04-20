package com.tw.demo.utils.redis;

import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.util.ShardInfo;
import redis.clients.util.Sharded;

public class JRedisShardInfo extends ShardInfo<JedisCommands> {
	private JedisShardInfo info;
	private Integer failedTimes = 0;
	private Boolean available;
	private final static int MAX_FAILED_TIMES = 10;
	private GenericObjectPoolConfig poolConfig;
	private List<JedisShardInfo> infos;

	public JRedisShardInfo(JedisShardInfo info) {
		super(Sharded.DEFAULT_WEIGHT);

		this.info = info;
		this.available = true;
	}

	public JRedisShardInfo(final GenericObjectPoolConfig poolConfig, List<JedisShardInfo> infos) {
		super(Sharded.DEFAULT_WEIGHT);

		this.poolConfig = poolConfig;
		this.infos = infos;
	}

	private void incFailedTimes() {
		failedTimes++;
	}

	private Boolean isNeededDetect() {
		return failedTimes > MAX_FAILED_TIMES;
	}

	public synchronized void handleError() {
		incFailedTimes();
		if (isNeededDetect()) {

		}
	}

	public Boolean isAvailable() {
		return this.available;
	}

	public String toString() {
		return info.toString();
	}

	@Override
	protected JedisCommands createResource() {
		String host = "";
		String password = "";
		int timeout = 2000;

		for (JedisShardInfo info : infos) {
			host += info.getHost() + ":" + info.getPort() + ";";
			password = info.getPassword();
			timeout = info.getTimeout();
		}
		/* 去除最后一个分号 */
		host = host.substring(0, host.length() - 1);
		return new JRedisPool(poolConfig, host, timeout, password).getJRedis();
	}

	@Override
	public String getName() {
		return null;
	}
}

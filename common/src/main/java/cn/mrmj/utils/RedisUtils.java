package cn.mrmj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * create by: mrmj
 * description: 对StringRedisTemplate进行再次封装，redis工具类
 * 方法定义：http://doc.redisfans.com
 * create time: 2019/11/21 17:43
 */
@Service
public class RedisUtils {

	@Autowired
    StringRedisTemplate stringRedisTemplate;

	/******************* key **********************/

	/**
	 * create by: mrmj
	 * description: 删除key
	 * create time: 2019/11/21 17:46
	 */
	public void del(String key) {
		stringRedisTemplate.delete(key);
	}

	/**
	 * create by: mrmj
	 * description: 批量删除key
	 * create time: 2019/11/21 17:48
	 */
	public void del(Collection<String> keys) {
		stringRedisTemplate.delete(keys);
	}

	/**
	 * create by: mrmj
	 * description: 检查给定 key是否存在
	 * create time: 2019/11/21 17:52
	 */
	public Boolean exists(String key) {
		return stringRedisTemplate.hasKey(key);
	}

	/**
	 * create by: mrmj
	 * description: 设置过期时间,单位 秒
	 * create time: 2019/11/21 18:01
	 */
	public Boolean expire(String key, long timeout) {
		return stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}

	/**
	 * create by: mrmj
	 * description: 设置过期时间
	 * create time: 2019/11/21 18:01
	 */
	public Boolean expire(String key, long timeout, TimeUnit timeUtit) {
		return stringRedisTemplate.expire(key, timeout, timeUtit);
	}

	/**
	 * create by: mrmj
	 * description: 设置过期时间
	 * create time: 2019/11/21 18:02
	 */
	public Boolean expireAt(String key, Date date) {
		return stringRedisTemplate.expireAt(key, date);
	}

	/**
	 * create by: mrmj
	 * description: 返回给定 key 的剩余生存时间,以秒为单位
	 * create time: 2019/11/21 18:06
	 */
	public Long ttl(String key) {
		return stringRedisTemplate.getExpire(key);
	}

	/******************* String **********************/

	/**
	 * create by: mrmj
	 * description: 将 key所储存的值加上增量 delta,返回增加后的值
	 * create time: 2019/11/21 18:07
	 */
	public Long incrBy(String key, long delta) {
		return stringRedisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * create by: mrmj
	 * description: 将字符串值 value 关联到 key
	 * create time: 2019/11/21 18:07
	 */
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	/**
	 * create by: mrmj
	 * description:  将字符串值 value 关联到 key
	 * create time: 2019/11/21 18:07
	 */
	public void setex(String key, String value, long timeout, TimeUnit unit) {
		stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	/**
	 * create by: mrmj
	 * description: 将 key的值设为 value ，当且仅当 key 不存在
	 * create time: 2019/11/21 18:08
	 */
	public Boolean setnx(String key, String value) {
		return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
	}

	/**
	 * create by: mrmj
	 * description: 关联到 key
	 * create time: 2019/11/21 18:08
	 */
	public void mset(Map<String, String> map) {
		stringRedisTemplate.opsForValue().multiSet(map);
	}

	/**
	 * create by: mrmj
	 * description: 返回 key所关联的字符串
	 * create time: 2019/11/21 18:09
	 */
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	/******************* Hash **********************/

	/**
	 * create by: mrmj
	 * description: 删除哈希表 key中的一个或多个指定域，不存在的域将被忽略
	 * create time: 2019/11/21 18:09
	 */
	public Long hdel(String key, String... hashKeys) {
		return stringRedisTemplate.opsForHash().delete(key, hashKeys);
	}

	/**
	 * create by: mrmj
	 * description: 将哈希表 key中的域 field 的值设为 value
	 * create time: 2019/11/21 18:09
	 */
	public void hset(String key, String hashKey, String hashValue) {
		stringRedisTemplate.opsForHash().put(key, hashKey, hashValue);
	}

	/**
	 * create by: mrmj
	 * description: 同时将多个 field-value (域-值)对设置到哈希表 key 中
	 * create time: 2019/11/21 18:09
	 */
	public void hmset(String key, Map<String, String> map) {
		stringRedisTemplate.opsForHash().putAll(key, map);
	}

	/**
	 * create by: mrmj
	 * description: 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在
	 * create time: 2019/11/21 18:10
	 */
	public Boolean hsetnx(String key, String hashKey, String hashValue) {
		return stringRedisTemplate.opsForHash().putIfAbsent(key, hashKey, hashValue);
	}

	/**
	 * create by: mrmj
	 * description:  返回哈希表 key 中给定域 field 的值
	 * create time: 2019/11/21 18:10
	 */
	public String hget(String key, String hashKey) {
		return (String) stringRedisTemplate.opsForHash().get(key, hashKey);
	}

	/**
	 * create by: mrmj
	 * description: 返回哈希表 key 中，所有的域和值
	 * create time: 2019/11/21 18:11
	 */
	public Map<Object, Object> hgetAll(String key) {
		return stringRedisTemplate.opsForHash().entries(key);
	}

	/**
	 * create by: mrmj
	 * description: 返回哈希表 key 中的所有域
	 * create time: 2019/11/21 18:12
	 */
	public Set<Object> hkeys(String key) {
		return stringRedisTemplate.opsForHash().keys(key);
	}

	/**
	 * create by: mrmj
	 * description: 返回哈希表 key 中的所有域
	 * create time: 2019/11/21 18:12
	 */
	public List<Object> hvals(String key) {
		return stringRedisTemplate.opsForHash().values(key);
	}

	/**
	 * create by: mrmj
	 * description: 为哈希表 key 中的域 field 的值加上增量 delta
	 * create time: 2019/11/21 18:12
	 */
	public Long hincrBy(String key, String hashKey, long delta) {
		return stringRedisTemplate.opsForHash().increment(key, hashKey, delta);
	}

	/**
	 * create by: mrmj
	 * description: 查看哈希表 key 中，给定域 field 是否存在
	 * create time: 2019/11/21 18:13
	 */
	public Boolean hexists(final String key, String hashKey) {
		return stringRedisTemplate.opsForHash().hasKey(key, hashKey);
	}

	/******************* List **********************/

	/**
	 * create by: mrmj
	 * description: 删除并获取列表中的第一个元素
	 * create time: 2019/11/21 18:13
	 */
	public String lpop(String key) {
		return stringRedisTemplate.opsForList().leftPop(key);
	}

	/**
	 * create by: mrmj
	 * description: 删除并获取列表中的第一个元素，或阻塞，直到有一个元素可用
	 * create time: 2019/11/21 18:13
	 */
	public String blpop(String key, long timeout, TimeUnit unit) {
		return stringRedisTemplate.opsForList().leftPop(key, timeout, unit);
	}

	/**
	 * create by: mrmj
	 * description: 删除并获取列表中的最后一个元素
	 * create time: 2019/11/21 18:13
	 */
	public String rpop(String key) {
		return stringRedisTemplate.opsForList().rightPop(key);
	}

	/**
	 * create by: mrmj
	 * description: 删除并获取列表中的最后一个元素，或阻塞，直到有一个元素可用
	 * create time: 2019/11/21 18:14
	 */
	public String brpop(String key, long timeout, TimeUnit unit) {
		return stringRedisTemplate.opsForList().rightPop(key, timeout, unit);
	}

	/**
	 * create by: mrmj
	 * description: 返回列表 key 的长度
	 * create time: 2019/11/21 18:14
	 */
	public Long llen(String key) {
		return stringRedisTemplate.opsForList().size(key);
	}

	/**
	 * create by: mrmj
	 * description: 将value 插入到列表 key 的表头
	 * create time: 2019/11/21 18:14
	 */
	public Long lpush(String key, String value) {
		return stringRedisTemplate.opsForList().leftPush(key, value);
	}


	/**
	 * create by: mrmj
	 * description: 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表
	 * create time: 2019/11/21 18:14
	 */
	public Long lpushx(String key, String value) {
		return stringRedisTemplate.opsForList().leftPushIfPresent(key, value);
	}

	/**
	 * create by: mrmj
	 * description: 将value 插入到列表 key 的表尾
	 * create time: 2019/11/21 18:15
	 */
	public Long rpush(String key, String value) {
		return stringRedisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * create by: mrmj
	 * description: 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表
	 * create time: 2019/11/21 18:15
	 */
	public Long rpushx(String key, String value) {
		return stringRedisTemplate.opsForList().rightPushIfPresent(key, value);
	}

	/******************* Set **********************/

	/**
	 * create by: mrmj
	 * description: 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略
	 * create time: 2019/11/21 18:20
	 */
	public Long sadd(String key, String... values) {
		return stringRedisTemplate.opsForSet().add(key, values);
	}

	/**
	 * create by: mrmj
	 * description: 返回集合 key 的基数(集合中元素的数量)
	 * create time: 2019/11/21 18:20
	 */
	public Long scard(String key) {
		return stringRedisTemplate.opsForSet().size(key);
	}

	/**
	 * create by: mrmj
	 * description: 返回一个集合的全部成员，该集合是所有给定集合之间的差集
	 * create time: 2019/11/21 18:20
	 */
	public Set<String> sdiff(String key, String otherKey) {
		return stringRedisTemplate.opsForSet().difference(key, otherKey);
	}

	/**
	 * create by: mrmj
	 * description: 返回一个集合的全部成员，该集合是所有给定集合之间的差集
	 * create time: 2019/11/21 18:20
	 */
	public Set<String> sdiff(String key, Collection<String> otherKeys) {
		return stringRedisTemplate.opsForSet().difference(key, otherKeys);
	}

	/**
	 * create by: mrmj
	 * description: 返回一个集合的全部成员，该集合是所有给定集合的交集
	 * create time: 2019/11/21 18:19
	 */
	public Set<String> sinter(String key, String otherKey) {
		return stringRedisTemplate.opsForSet().intersect(key, otherKey);
	}

	/**
	 * create by: mrmj
	 * description: 返回一个集合的全部成员，该集合是所有给定集合的交集
	 * create time: 2019/11/21 18:19
	 */
	public Set<String> sinter(String key, Collection<String> otherKeys) {
		return stringRedisTemplate.opsForSet().intersect(key, otherKeys);
	}

	/**
	 * create by: mrmj
	 * description: 判断 member 元素是否集合 key 的成员
	 * create time: 2019/11/21 18:19
	 */
	public Boolean sismember(String key, String member) {
		return stringRedisTemplate.opsForSet().isMember(key, member);
	}

	/**
	 * create by: mrmj
	 * description: 返回集合 key 中的所有成员
	 * create time: 2019/11/21 18:19
	 */
	public Set<String> smembers(String key) {
		return stringRedisTemplate.opsForSet().members(key);
	}

	/**
	 * create by: mrmj
	 * description: 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略
	 * create time: 2019/11/21 18:19
	 */
	public Long srem(String key, String... values) {
		return stringRedisTemplate.opsForSet().remove(key, values);
	}

	/**
	 * create by: mrmj
	 * description: 返回一个集合的全部成员，该集合是所有给定集合的并集
	 * create time: 2019/11/21 18:19
	 */
	public Set<String> sunion(String key, String otherKey) {
		return stringRedisTemplate.opsForSet().union(key, otherKey);
	}

	/**
	 * create by: mrmj
	 * description: 返回一个集合的全部成员，该集合是所有给定集合的并集
	 * create time: 2019/11/21 18:18
	 */
	public Set<String> sunion(String key, Collection<String> otherKeys) {
		return stringRedisTemplate.opsForSet().union(key, otherKeys);
	}

	/******************* Zset **********************/

	/**
	 * create by: mrmj
	 * description: 将一个或多个 member 元素及其 score 值加入到有序集 key 当中v
	 * create time: 2019/11/21 18:18
	 */
	public Boolean zadd(String key, String value, double score) {
		return stringRedisTemplate.opsForZSet().add(key, value, score);
	}

	/**
	 * create by: mrmj
	 * description: 返回有序集 key 的基数
	 * create time: 2019/11/21 18:18
	 */
	public Long zcard(String key) {
		return stringRedisTemplate.opsForZSet().zCard(key);
	}

	/**
	 * create by: mrmj
	 * description: 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max)的成员的数量
	 * create time: 2019/11/21 18:18
	 */
	public Long zcount(String key, double min, double max) {
		return stringRedisTemplate.opsForZSet().count(key, min, max);
	}

	/**
	 * create by: mrmj
	 * description: 为有序集 key 的成员 member 的 score 值加上增量 delta
	 * create time: 2019/11/21 18:18
	 */
	public Double zincrby(String key, String value, double delta) {
		return stringRedisTemplate.opsForZSet().incrementScore(key, value, delta);
	}

	/**
	 * create by: mrmj
	 * description: 返回有序集 key 中，指定区间内的成员,其中成员的位置按 score 值递增(从小到大)来排序
	 * create time: 2019/11/21 18:17
	 */
	public Set<String> zrange(String key, long start, long end) {
		return stringRedisTemplate.opsForZSet().range(key, start, end);
	}

	/**
	 * create by: mrmj
	 * description: 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max)的成员。有序集成员按
	 * 	 * score,值递增(从小到大)次序排列
	 * create time: 2019/11/21 18:17
	 */
	public Set<String> zrangeByScore(String key, double min, double max) {
		return stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
	}

	/**
	 * create by: mrmj
	 * description: 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。排名以 0 为底
	 * create time: 2019/11/21 18:17
	 */
	public Long zrank(String key, String member) {
		return stringRedisTemplate.opsForZSet().rank(key, member);
	}

	/**
	 * create by: mrmj
	 * description: 移除有序集 key 中，指定排名(rank)区间内的所有成员
	 * create time: 2019/11/21 18:17
	 */
	public Long zremrangeByRank(String key, long start, long end) {
		return stringRedisTemplate.opsForZSet().removeRange(key, start, end);
	}

	/**
	 * create by: mrmj
	 * description: 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员
	 * create time: 2019/11/21 18:17
	 */
	public Long zremrangeByScore(String key, double min, double max) {
		return stringRedisTemplate.opsForZSet().removeRangeByScore(key, min, max);
	}

	/**
	 * create by: mrmj
	 * description: 返回有序集 key 中，指定区间内的成员。其中成员的位置按 score 值递减(从大到小)来排列。
	 * create time: 2019/11/21 18:16
	 */
	public Set<String> zrevrange(String key, long start, long end) {
		return stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
	}

	/**
	 * create by: mrmj
	 * description: 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min)的所有的成员。有序集成员按
	 * 	 * score,值递减(从大到小)的次序排列
	 * create time: 2019/11/21 18:16
	 */
	public Set<String> zrevrangeByScore(String key, double min, double max) {
		return stringRedisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
	}

	/**
	 * create by: mrmj
	 * description: 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。排名以 0 为底
	 * create time: 2019/11/21 18:16
	 */
	public Long zrevrank(String key, String member) {
		return stringRedisTemplate.opsForZSet().reverseRank(key, member);
	}

	/**
	 * create by: mrmj
	 * description: 返回有序集 key 中，成员 member 的 score 值
	 * create time: 2019/11/21 18:15
	 */
	public Double zscore(String key, String member) {
		return stringRedisTemplate.opsForZSet().score(key, member);
	}

	/******************* Pub/Sub **********************/

	/**
	 * create by: mrmj
	 * description: 将信息 message 发送到指定的频道 channel
	 * create time: 2019/11/21 18:15
	 */
	public void publish(String channel, String message) {
		stringRedisTemplate.convertAndSend(channel, message);
	}

	/******************* serial **********************/

	/**
	 * create by: mrmj
	 * description: 获取redisTemplate的序列化
	 * create time: 2019/11/21 18:15
	 */
	public RedisSerializer<String> getSerializer() {
		return stringRedisTemplate.getStringSerializer();
	}

}

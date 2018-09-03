package com.build.cloud.common.id;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.build.cloud.common.constant.Constant.modules;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.modules.common.service.ICommonService;
import com.google.common.collect.Maps;

@Component
public class IdGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(IdGenerator.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	// 分布式锁的锁定时长，单位秒
	private static final int LOCK_TIME = 5;
	// 时间单位
	private static TimeUnit timeUnit = TimeUnit.SECONDS;
	// 超时时间
	private static long timeOut = 5;
	// 超时时间
	public static String LOCK_PREFIX = "ID:ID_LOCK_";

	@Autowired
	private ICommonService commonService;

	/**
	 * id生成器
	 * 
	 * @param lockKey
	 *          锁，根据不同的模块设定，设定时不要重复
	 * @param length
	 *          流水长度
	 * @param prefix
	 *          流水前缀
	 * @param projectId
	 *          项目id
	 * @param type
	 *          采购合同类型、劳务分包（暂时）其他模块可传NULL 类型
	 * @return
	 */
	public String getNewMax(String lockKey, int length, String prefix, String projectId, String type) {
		String newMaxValue = null;
		long start = System.nanoTime();
		//是否是劳务分包
		boolean isLs = modules.LS_CONTRACT_LABOR.getValue().equals(lockKey);
		// 是否是采购合同
		boolean isPurcCont = modules.PURCHASE_CONTRACT.getValue().equals(lockKey);
		boolean isContract = isPurcCont || isLs;
		do {
			if (isContract) {
				lockKey = LOCK_PREFIX + StringUtil.upperCase(lockKey) + "_" + type;
			} else {
				lockKey = LOCK_PREFIX + StringUtil.upperCase(lockKey);
			}
			String lockValue = String.valueOf(new Date().getTime());
			boolean lockFlag = stringRedisTemplate.getConnectionFactory()
			    .getConnection()
			    .setNX(lockKey.getBytes(), lockValue.getBytes())
			    .booleanValue();
			// 获取锁
			if (lockFlag) {
				String idKey = "";
				if (isContract) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("conType", type);
					map.put("projectId", projectId);
					if (isPurcCont) {
						// 采购合同 从数据库 检索单据号最大值 并存入 redis缓存
						map.put("tableName", "mat_purchase_contract");
					} else if (isLs) {
						// 劳务分包
						map.put("tableName", "ls_contract_labor");
					}
					String maxNum = commonService.selectMaxCode(map);
					if (maxNum == null || "".equals(maxNum)) {
						maxNum = "000";
					}
					idKey = lockKey + "_" + projectId + "_" + type;
					stringRedisTemplate.opsForValue().set(idKey, maxNum);
				} else {
					idKey = lockKey + "_" + projectId;
				}
				// 1、设置有效期，防止当前锁异常或崩溃导致锁释放失败
				stringRedisTemplate.expire(lockKey, LOCK_TIME, TimeUnit.SECONDS);
				// 2、获取当前最大编码值
				String currentMaxValue = stringRedisTemplate.opsForValue().get(idKey);
				// 如果redis中该值丢失，重新执行初始化
				if (StringUtil.isBlank(currentMaxValue)) {
					String suffix = String.format("%0" + length + "d", 0);
					stringRedisTemplate.opsForValue().set(idKey, suffix);
					currentMaxValue = stringRedisTemplate.opsForValue().get(idKey);
				}

				// 3、将最大值加1，获取新的最大值
				int currentMaxNum = Integer.parseInt(currentMaxValue);
				newMaxValue = String.format("%0" + length + "d", currentMaxNum + 1);
				// 4、将新的最大值同步到redis缓存
				stringRedisTemplate.opsForValue().set(idKey, newMaxValue);

				newMaxValue = prefix + newMaxValue;

				// 5、释放锁，redis执行删除方法
				stringRedisTemplate.delete(lockKey);

				break;
				// 未获取锁
			} else {
				LOGGER.info(Thread.currentThread().getName() + "=====未获取锁,未超时将进入循环");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 如果未超时，则循环获取锁
		} while (System.nanoTime() - start < timeUnit.toNanos(timeOut));

		return newMaxValue;
	}

}

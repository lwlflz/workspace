package com.build.cloud.modules.sys.service.impl;
import java.awt.image.BufferedImage;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.DateUtils;
import com.build.cloud.modules.sys.dao.SysCaptchaDao;
import com.build.cloud.modules.sys.entity.SysCaptchaEntity;
import com.build.cloud.modules.sys.service.ISysCaptchaService;
import com.google.code.kaptcha.Producer;
/**
 * @ClassName: SysCaptchaServiceImpl
 * @Description: 验证码
 * @author: liutao
 * @date: 2018年3月31日 下午3:59:23
 */
@Service("sysCaptchaService")
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaDao, SysCaptchaEntity> implements ISysCaptchaService {
	@Autowired
	private Producer producer;
	@Override
	public BufferedImage getCaptcha(String uuid) {
		if (StringUtils.isBlank(uuid)) {
			throw new BusinessException("uuid不能为空");
		}
		// 生成文字验证码
		String code = producer.createText();
		SysCaptchaEntity captchaEntity = new SysCaptchaEntity();
		captchaEntity.setUuid(uuid);
		captchaEntity.setCode(code);
		// 5分钟后过期
		captchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), 5));
		this.insert(captchaEntity);
		return producer.createImage(code);
	}
	@Override
	public boolean validate(String uuid, String code) {
		SysCaptchaEntity captchaEntity = this.selectOne(new EntityWrapper<SysCaptchaEntity>().eq("uuid", uuid));
		if (captchaEntity == null) {
			return false;
		}
		// 删除验证码
		this.deleteById(uuid);
		if (captchaEntity.getCode().equalsIgnoreCase(code)
			&& captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis()) {
			return true;
		}
		return false;
	}
}

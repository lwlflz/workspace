package com.build.cloud.modules.sys.service;

import java.awt.image.BufferedImage;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.sys.entity.SysCaptchaEntity;

/**
 * 
 * @ClassName: SysCaptchaService   
 * @Description: 验证码  
 * @author: liutao
 * @date: 2018年3月31日 下午3:57:17
 */
public interface ISysCaptchaService extends IService<SysCaptchaEntity> {

    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}

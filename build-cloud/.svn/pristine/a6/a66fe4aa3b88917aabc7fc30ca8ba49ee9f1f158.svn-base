package com.build.cloud.modules.sms.service.impl;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.build.cloud.modules.sms.service.IMobileCodeService;
import com.build.cloud.modules.sys.form.MagLongForm;
import com.build.cloud.modules.sys.util.SendCode;

@Service("mobileCodeService")
public class MobileCodeServiceImpl implements IMobileCodeService {	
	
	@Override
	public MagLongForm singleHairSms(String phone, String templateId) {
		MagLongForm form = new MagLongForm();
		form.setPhone(phone);		
		String ret = "";
		try {
			ret = SendCode.sendMsg(phone, templateId);			
		} catch (IOException e) {
			e.printStackTrace();
		}			
		if("success".equals(ret)){
			form.setStatus("发送成功！");
		}else {
			form.setStatus("发送失败！");
		}
		return form;
	}

	@Override
	public List<MagLongForm> multipleHairSms(String[] phones, String templateId) {
		List<MagLongForm> retList = new ArrayList<MagLongForm>();
		for(String phone :phones) {
			MagLongForm form = new MagLongForm();
			form.setPhone(phone);		
			String ret = "";
			try {
				ret = SendCode.sendMsg(phone, templateId);			
			} catch (IOException e) {
				e.printStackTrace();
			}			
			if("success".equals(ret)){
				form.setStatus("发送成功！");
			}else {
				form.setStatus("发送失败！");
			}
			retList.add(form);
		}
		return retList;
	}
}

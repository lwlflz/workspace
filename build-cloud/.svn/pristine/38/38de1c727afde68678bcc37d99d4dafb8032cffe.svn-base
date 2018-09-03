package com.build.cloud.modules.sys.util;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.build.cloud.modules.sys.entity.SysMsgLogEntity;
import com.build.cloud.modules.sys.service.ISysMsgLogService;
import com.google.common.collect.Lists;
public class SendCode {
	@Autowired
	private static ISysMsgLogService sysMsgLogService;
	private static final String SERVER_URL = "https://api.netease.im/sms/sendcode.action";// 发送验证码的请求路径URL
	private static final String APP_KEY = "19815ee574d59dea3e18c53ebbc2a2f3";// 网易云信分配的账号
	private static final String APP_SECRET = "3b9d91470dac";// 网易云信分配的密钥
	private static final String NONCE = "123456";// 随机数
	public static String sendMsg(String phone,String templateId)
		throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(SERVER_URL);
		String curTime = String.valueOf((new Date().getTime() / 1000L));
		String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);
		// 设置请求的header
		post.addHeader("AppKey", APP_KEY);
		post.addHeader("Nonce", NONCE);
		post.addHeader("CurTime", curTime);
		post.addHeader("CheckSum", checkSum);
		post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		// 设置请求参数
		List<NameValuePair> nameValuePairs = Lists.newArrayList();
		nameValuePairs.add(new BasicNameValuePair("mobile", phone));
		nameValuePairs.add(new BasicNameValuePair("codeLen", "6"));
		nameValuePairs.add(new BasicNameValuePair("templateid", templateId));
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
		// 执行请求0
		HttpResponse response = httpclient.execute(post);
		String responseEntity = EntityUtils.toString(response.getEntity(), "utf-8");
		// 判断是否发送成功，发送成功返回true
		String code = JSON.parseObject(responseEntity).getString("code");
		SysMsgLogEntity ent = new SysMsgLogEntity();
		ent.setReceiveCode(phone);
		ent.setMsgType("0");
		ent.setMsgContent(checkSum);
		ent.setSendDate(new Date());
		String ret = "";
		if (code.equals("200")) {
			ent.setSendStatus("0");
			ret = "success";
		} else {
			ent.setSendStatus("1");
			ret = "error";
		}
		msgLogs(ent);
		return ret;
	}
	private static void msgLogs(SysMsgLogEntity ent) {
		sysMsgLogService.insert(ent);
	}
	public static void main(String[] args) {
		String mobileNumber = "13607430006";// 接收验证码的手机号码
		try {
			String str = sendMsg(mobileNumber,"3972577");
			if ("success".equals(str)) {
				System.out.println("发送成功！");
			} else {
				System.out.println("发送失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

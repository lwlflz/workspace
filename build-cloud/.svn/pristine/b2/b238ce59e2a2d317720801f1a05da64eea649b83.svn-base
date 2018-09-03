package com.build.cloud;
import org.apache.shiro.SecurityUtils;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.build.cloud.modules.punch.service.ISyncService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptTest {
	@Autowired
	private StringEncryptor stringEncryptor;
	@Autowired
	private ISyncService syncService;
	@Autowired
	WebApplicationContext wac;
	@Autowired
	org.apache.shiro.mgt.SecurityManager securityManager;
	private MockMvc mockMvc;
	@Before
	public void brfore() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		SecurityUtils.setSecurityManager(securityManager);
	}
	@Test
	public void testEnvironmentProperties() {
		System.out.println(stringEncryptor.encrypt("Ddjs!123456")); // d
		System.out.println(stringEncryptor.encrypt("Ddjs!654321")); // t
		System.out.println(stringEncryptor.encrypt("Ddjs!312888")); // p
	}
	@Test
	public void syncDevTest() {
		syncService.syncDev();
	}
	@Test
	public void syncUserTest() {
		syncService.syncUser();
	}
}

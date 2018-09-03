package com.build.cloud.modules.sys.service.impl;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.build.cloud.common.utils.AddressUtils;
import com.build.cloud.modules.sys.bean.UserOnline;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.service.ISessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

@Service("sessionService")
public class SessionServiceImpl implements ISessionService {

	@Autowired
	private SessionDAO sessionDAO;

	@Autowired
	ObjectMapper mapper;

	@Override
	public List<UserOnline> list() {
		List<UserOnline> list = Lists.newArrayList();
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		for (Session session : sessions) {
			UserOnline userOnline = new UserOnline();
			SysUserEntity user = new SysUserEntity();
			SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
			if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
				continue;
			} else {
				principalCollection = (SimplePrincipalCollection) session
						.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
				user = (SysUserEntity) principalCollection.getPrimaryPrincipal();
				userOnline.setUserName(user.getUserName());
				userOnline.setUserId(user.getId());
				userOnline.setLoginName(user.getUserName());
			}
			userOnline.setId((String) session.getId());
			userOnline.setHost(session.getHost());
			userOnline.setStartTimestamp(session.getStartTimestamp());
			userOnline.setLastAccessTime(session.getLastAccessTime());
			Long timeout = session.getTimeout();
			if (timeout == 0l) {
				userOnline.setStatus("0");
			} else {
				userOnline.setStatus("1");
			}
			String address = AddressUtils.getRealAddressByIP(userOnline.getHost(), mapper);
			userOnline.setLocation(address);
			userOnline.setTimeout(timeout);
			list.add(userOnline);
		}
		return list;
	}

	@Override
	public boolean forceLogout(String sessionId) {
		Session session = sessionDAO.readSession(sessionId);
		session.setTimeout(0);
		return true;
	}

}

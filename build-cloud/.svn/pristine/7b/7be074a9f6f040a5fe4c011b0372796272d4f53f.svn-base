package com.build.cloud.modules.sys.service.impl;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.collection.CollectionUtil;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.sys.dao.SysRoleMenuDao;
import com.build.cloud.modules.sys.entity.SysRoleMenuEntity;
import com.build.cloud.modules.sys.service.ISysRoleMenuService;
import com.google.common.collect.Lists;

/**
 * @ClassName: SysRoleMenuServiceImpl
 * @Description: 角色与菜单对应关系
 * @author: liutao
 * @date: 2018年3月16日 下午5:35:04
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity>
		implements ISysRoleMenuService {

	private ExecutorService executorService = Executors.newCachedThreadPool();

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(String roleId, List<String> menuIdList) {
		if (CollectionUtil.isEmpty(menuIdList)) {
			return;
		}
		// 先删除角色与菜单关系
		deleteBatch(new String[] { roleId });

		// 保存角色与菜单关系
		List<SysRoleMenuEntity> list = Lists.newArrayListWithCapacity(menuIdList.size());
		for (String menuId : menuIdList) {
			SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
			sysRoleMenuEntity.setMenuId(menuId);
			sysRoleMenuEntity.setRoleId(roleId);
			list.add(sysRoleMenuEntity);
		}
		//this.insertBatch(list);
		// TODO: 新增数据   异步执行  （还需优化）
//		Thread  thread = new Thread() {
//			@Override
//			public void run() {
//				System.out.println("###########线程执行#####################");
//				insertBatch(list);
//			}
//		};
//	
//		thread.run();
		
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				insertBatch(list);
			}
		});
		
	}

	@Override
	public List<String> queryMenuIdList(String roleId) {
		return baseMapper.queryMenuIdList(roleId);
	}

	@Override
	public int deleteBatch(String[] roleIds) {
		return baseMapper.deleteBatch(roleIds);
	}
	
	
	
//	  public static void main(String[] args)
//	    {
//	        //System.out.println("主线程" + Thread.currentThread().getName());
//	        Runnable myRunnable = new Runnable()
//	        {
//	            @Override
//	            public void run()
//	            {
//	                System.out.println(Thread.currentThread().getName());
//	            }
//	        };
//	        myRunnable.run();
//	    }
}

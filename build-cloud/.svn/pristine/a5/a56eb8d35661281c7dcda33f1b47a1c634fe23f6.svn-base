package com.build.cloud.modules.mat.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.activity.pojo.TaskIdMapPojo;
import com.build.cloud.common.activity.service.ActivityService;
import com.build.cloud.common.constant.ActivityConstant;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.common.service.ICommonService;
import com.build.cloud.modules.mat.dao.MatPurchaseStockOutDao;
import com.build.cloud.modules.mat.entity.MatPurchaseStockEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockOutEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockOutlistEntity;
import com.build.cloud.modules.mat.service.IMatPurchaseStockOutService;
import com.build.cloud.modules.mat.service.IMatPurchaseStockOutlistService;
import com.build.cloud.modules.mat.service.IMatPurchaseStockService;
import com.build.cloud.modules.productplan.dto.ProContract;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

/**
 * <p>
 * 出库表 服务实现类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
@Service
public class MatPurchaseStockOutServiceImpl extends ServiceImpl<MatPurchaseStockOutDao, MatPurchaseStockOutEntity>
		implements IMatPurchaseStockOutService {

	@Autowired
	private IMatPurchaseStockOutlistService matPurchaseStockOutlistService;

	@Autowired
	private MatPurchaseStockOutDao matPurchaseStockOutDao;

	@Autowired
	private IMatPurchaseStockService matPurchaseStockService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ICommonService commonService;

	@Override
	public PageUtils queryPageList(Map<String, Object> params) {
		Query<MatPurchaseStockOutEntity> query = new Query<MatPurchaseStockOutEntity>(params);
		Page<MatPurchaseStockOutEntity> page = query.getPage();
		page.setRecords(baseMapper.queryPageList(page, params));
		return new PageUtils(page);
	}

	@Override
	@Transactional
	public MatPurchaseStockOutEntity save(MatPurchaseStockOutEntity entity) {
		baseMapper.insert(entity);
		List<MatPurchaseStockOutlistEntity> slEntities = entity.getList();
		for (MatPurchaseStockOutlistEntity OutlistEntity : slEntities) {
			OutlistEntity.setStockId(entity.getId());
		}
		matPurchaseStockOutlistService.insertBatch(slEntities);
		return entity;
	}

	@Override
	public MatPurchaseStockOutEntity info(String id) {
		MatPurchaseStockOutEntity entity = baseMapper.selectById(id);
		if (entity != null) {
			List<MatPurchaseStockOutlistEntity> list = matPurchaseStockOutlistService.selectListInfo(id);
			entity.setList(list);
		}
		return entity;
	}

	@Override
	@Transactional
	public void update(MatPurchaseStockOutEntity entity) {
		baseMapper.updateById(entity);
		EntityWrapper<MatPurchaseStockOutlistEntity> wrapper = new EntityWrapper<MatPurchaseStockOutlistEntity>();
		wrapper.eq("stock_id", entity.getId());
		matPurchaseStockOutlistService.delete(wrapper);
		matPurchaseStockOutlistService.insertBatch(entity.getList());
	}

	@Override
	public Integer getInventory(Map<String, Object> params) {
		return matPurchaseStockOutDao.getInventory(params);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void submit(MatPurchaseStockOutEntity entity) {
		// 获取出库信息
		MatPurchaseStockOutEntity info = baseMapper.selectById(entity.getId());

		// 如果流程存在且未完成
		// `check_status` char(1) NOT NULL DEFAULT '0' COMMENT
		// '状态(0-自由态;1-审核中;2-审核通过)',
		// `return_status` char(1) DEFAULT '0' COMMENT '弃审状态(0-未弃审，1-已弃审)',
		// `activityins_id` varchar(32) DEFAULT '' COMMENT '工作流实例id',
		if (!StringUtils.isEmpty(info.getActivityinsId()) && Objects.equal(info.getCheckStatus(), "0")
				&& Objects.equal(info.getReturnStatus(), "0")) {
			// 完成节点
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setUserId(ShiroUtils.getUserId());
			Map<String, Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
			tmp.setLocalMap(localMap);
			// 根据流程实例id完成当前节点
			activityService.completeTaskByInsId(info.getActivityinsId(), tmp);

			// 修改出库审核工作流状态
			if (!StringUtils.isEmpty(info.getActivityinsId())) {
				MatPurchaseStockOutEntity upInfo = new MatPurchaseStockOutEntity();
				upInfo.setId(entity.getId());
				upInfo.setActivityinsId(info.getActivityinsId());
				upInfo.setCheckStatus("1");// 审核中
				upInfo.setReturnStatus("0");// 未弃审
				baseMapper.updateById(upInfo);
			}
		} else if ((StringUtils.isEmpty(info.getActivityinsId()) || Objects.equal(info.getReturnStatus(), "1"))
				&& Objects.equal(info.getCheckStatus(), "0")) {// 如果流程不存在或流程已弃审
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setInsKey(ActivityConstant.ProcessKey.ACT_DEF_PU_STOCKOUT.value);// 流程定义key
			tmp.setUserId(ShiroUtils.getUserId());

			Map<String, Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
			tmp.setLocalMap(localMap);

			Map<String, Object> map = Maps.newHashMap();
			map.put(ActivityConstant.VariableKey.ACT_VAR_SUBMITER.value, ShiroUtils.getUserId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLCODE.value, info.getId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLTYPE.value,
					ActivityConstant.BillType.ACT_BILL_PU_STOCKOUT.value);
			map.put(ActivityConstant.VariableKey.ACT_VAR_PROJECTNAME.value, info.getProjectName());
			tmp.setMap(map);
			// 启动流程实例并完成第一个节点
			String activityInsId = activityService.startAndComplete(tmp);

			// 修改出库审核工作流状态
			if (!StringUtils.isEmpty(activityInsId)) {
				MatPurchaseStockOutEntity upInfo = new MatPurchaseStockOutEntity();
				upInfo.setId(info.getId());
				upInfo.setActivityinsId(activityInsId);
				upInfo.setCheckStatus("1");// 审核中
				upInfo.setReturnStatus("0");// 未弃审
				baseMapper.updateById(upInfo);
			}
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void check(MatPurchaseStockOutEntity entity) {
		TaskIdMapPojo taskPojo = new TaskIdMapPojo();
		taskPojo.setTaskId(entity.getTaskId());
		// 批注
		taskPojo.setComment(entity.getComment());
		taskPojo.setUserId(ShiroUtils.getUserId());

		Map<String, Object> localMap = Maps.newHashMap();
		localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_PASS.value);
		taskPojo.setLocalMap(localMap);
		// 修改出库审核工作流状态
		activityService.completeTaskByTaskIdAndMap(taskPojo);

		ProcessInstance pi = activityService.queryProcessInstance(entity.getActivityinsId());
		// 流程结束
		if (pi == null) {
			// 修改单据状态为审核通过
			MatPurchaseStockOutEntity upInfo = new MatPurchaseStockOutEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("2");// 审核通过
			// upInfo.setEffStatus("1");//已生效
			upInfo.setCheckFinTime(new Date());
			baseMapper.updateById(upInfo);

			// 审批 通过 修改库存
			updateMatPurchaseStockEntity(entity.getId());
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void reject(MatPurchaseStockOutEntity entity) {
		// 工作流驳回
		TaskIdMapPojo taskPojo = new TaskIdMapPojo();
		taskPojo.setTaskId(entity.getTaskId());
		taskPojo.setComment(entity.getComment());
		taskPojo.setUserId(ShiroUtils.getUserId());

		// Map<String,Object> localMap = Maps.newHashMap();
		// localMap.put("oper",
		// ActivityConstant.TaskOperType.ACT_TASKOPER_REJECT.value);
		// taskPojo.setLocalMap(localMap);

		boolean isReturnSubmit = activityService.backProcess(taskPojo);
		if (isReturnSubmit) {
			// 修改单据状态为自由态
			MatPurchaseStockOutEntity upInfo = new MatPurchaseStockOutEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");// 自由态
			baseMapper.updateById(upInfo);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void endReturn(MatPurchaseStockOutEntity entity) {
		MatPurchaseStockOutEntity stockOutEntity = baseMapper.selectById(entity.getId());
		Map<String, Object> params = Maps.newHashMap();
		params.put("id", stockOutEntity.getId());
		params.put("projectId", stockOutEntity.getProjectId());
		Integer times = baseMapper.selectTakeTimes(params);
		if (times != null && times >= 1) {
			throw new BusinessException("该出库单已经做过盘存，不能弃审");
		}
		ProcessInstance ins = activityService.findProcessInstance(entity.getActivityinsId());
		if (ins == null) {// 表示流程结束
			// 库存量还原
			reductionStock(entity.getId());
			// 修改单据状态为自由态，已弃审
			MatPurchaseStockOutEntity upInfo = new MatPurchaseStockOutEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");// 自由态
			upInfo.setReturnStatus("1");// 已弃审
			baseMapper.updateById(upInfo);
		}

	}

	@Override
	public boolean updateMatPurchaseStockEntity(String id) {
		boolean isUpdate = false; // 是否修改成功
		// 得到出库信息
		MatPurchaseStockOutEntity outEntity = this.info(id);
		// 得到出库详情信息
		List<MatPurchaseStockOutlistEntity> list = outEntity.getList();
		if (list != null && list.size() > 0) {
			for (MatPurchaseStockOutlistEntity matPurchaseStockOutlistEntity : list) {
				// 获取 出库单 详情 数据 并校验 库存中是否存在数据
				Map<String, Object> columnMap = Maps.newHashMap();
				columnMap.put("project_id", outEntity.getProjectId());
				columnMap.put("mtr_code", matPurchaseStockOutlistEntity.getMtrCode());
				List<MatPurchaseStockEntity> StockEntityList = matPurchaseStockService.selectByMap(columnMap);
				if (StockEntityList != null && StockEntityList.size() > 0) {
					MatPurchaseStockEntity stockEntity = new MatPurchaseStockEntity();
					// 新出库数量
					BigDecimal outNumber = matPurchaseStockOutlistEntity.getOutNumber();
					// 当前库存量 = 原来库存量 - 新出库数量
					stockEntity.setStockNumber(StockEntityList.get(0).getStockNumber().subtract(outNumber));
					// 累计出库量 = 原来的出库量 +新出库的数量
					stockEntity.setTotalOutNumber(StockEntityList.get(0).getTotalOutNumber().add(outNumber));
					// 修改主键ID
					stockEntity.setId(StockEntityList.get(0).getId());
					matPurchaseStockService.updateById(stockEntity);
				}
			}
		}

		return isUpdate;
	}

	/**
	 * 审批前 校验 出库数量是否大于库存
	 */
	@Override
	public String verifyStock(MatPurchaseStockOutEntity entity) {
		String flag = "";
		MatPurchaseStockOutEntity outEntity = this.info(entity.getId());
		// 得到出库详情信息
		List<MatPurchaseStockOutlistEntity> list = outEntity.getList();
		for (MatPurchaseStockOutlistEntity stockOutlistEntity : list) {
			Map<String, Object> columnMap = Maps.newHashMap();
			columnMap.put("project_id", outEntity.getProjectId());
			columnMap.put("mtr_code", stockOutlistEntity.getMtrCode());
			List<MatPurchaseStockEntity> StockEntityList = matPurchaseStockService.selectByMap(columnMap);
			if (StockEntityList != null && StockEntityList.size() > 0) {
				if (stockOutlistEntity.getOutNumber().compareTo(StockEntityList.get(0).getStockNumber()) == 1) {
					flag = "材料名称: （" + stockOutlistEntity.getMtrName() + "） 出库数量：" + stockOutlistEntity.getOutNumber()
							+ "大于  库存数量： " + StockEntityList.get(0).getStockNumber();
				}
			}
		}
		return flag;
	}

	@Override
	public PageUtils findMaterialsList(Map<String, Object> params) {
		Query<MatPurchaseStockEntity> query = new Query<MatPurchaseStockEntity>(params);
		Page<MatPurchaseStockEntity> page = query.getPage();
		page.setRecords(baseMapper.queryStockPageList(page, params));
		return new PageUtils(page);
	}

	@Override
	public PageUtils getConList(Map<String, Object> params) {
		Map<String, String> map = commonService.getCompanyProType(params.get("companyId").toString(),
				params.get("projectId").toString());
		params.put(Constant.PRO_ID, map.get(Constant.PRO_ID));
		Query<ProContract> query = new Query<ProContract>(params);
		Page<ProContract> page = query.getPage();
		page.setRecords(baseMapper.getConList(page, params));
		return new PageUtils(page);
	}

	/**
	 * 弃审更新库存
	 * 
	 * @param id 入库主键
	 */
	public void reductionStock(String id) {
		// 得到出库信息
		MatPurchaseStockOutEntity entity = this.info(id);
		// 得到出库详情信息
		List<MatPurchaseStockOutlistEntity> list = entity.getList();
		// 修改库存量，当驳回的时候更新库存量
		for (MatPurchaseStockOutlistEntity ilEntity : list) {
			EntityWrapper<MatPurchaseStockEntity> wrapper = new EntityWrapper<MatPurchaseStockEntity>();
			wrapper.eq("project_id", entity.getProjectId());
			wrapper.eq("mtr_code", ilEntity.getMtrCode());
			MatPurchaseStockEntity stockEntity = matPurchaseStockService.selectOne(wrapper);
			// 当前库存量 = 原来库存量 - 新出库数量
			// 累计出库量 = 原来的出库量 +新出库的数量
			stockEntity.setStockNumber(stockEntity.getStockNumber().add(ilEntity.getOutNumber()));
			stockEntity.setTotalInNumber(stockEntity.getTotalInNumber().add(ilEntity.getOutNumber()));
			matPurchaseStockService.updateById(stockEntity);
		}
	}
}

package com.build.cloud.modules.productplan.service;

import java.util.List;
import java.util.Map;

import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.bs.entity.BsRectificationEntity;
import com.build.cloud.modules.productplan.dto.ProContract;
import com.build.cloud.modules.productplan.dto.ProContractPlandetail;
import com.build.cloud.modules.productplan.dto.ProPlanDetail;
import com.build.cloud.modules.productplan.dto.ProPlanInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangsen
 * @since 2018-04-23
 */
public interface IProContractService extends IService<ProContract> {
	/**
	 * 列表
	 * @param contract
	 * @return
	 */
	PageUtils selectConList(Map<String, Object> params,String companyId);
	/**
	 * 保存合同(包含文本、付款协议、工程量清单)
	 * @param proContract
	 */
	void insertProContract(ProContract proContract);
	
	/**
	 * 修改合同(包含文本、付款协议、工程量清单)
	 * @param proContract
	 */
	void updateProContract(ProContract proContract);
	
	/**
	 * 查询合同(包含文本、付款协议、工程量清单)
	 * @param proContract
	 */
	ProContract selectProContractById(ProContract proContract);
	
	/**
	 * 合同拆分展示
	 * @param proContract
	 */
	List<ProContractPlandetail> selectConSplit(ProContract proContract);
	
	/**
	 * 合同拆分生产计划选择界面查询
	 * @param proContract
	 */
	List<ProPlanDetail> selectConSplitPlan(ProContract proContract);
	
	/**
	 * 合同产值
	 * @param proContract
	 * @return
	 */
	List<ProContractPlandetail> selectValue(ProContract proContract);
	
	/**
	 * 合同拆分保存
	 * @param proContract
	 */
	void insertConSplit(ProContract proContract);
	
	/**
     * 合同拆分修改数值保存
     * @param info
     * @return
     */
	void saveConSplitValue(ProContract proContract);
	
	/**
	 * 提交审核，远程调用创建activity工作流实例
	 * @param proPlanInfo
	 */
	void submit(ProContract proContract);
	
	/**
	 * 管理员审核通过操作
	 */
	void check(ProContract proContract);
	
	/**
	 * 管理员审核驳回操作
	 */
	void reject(ProContract proContract);
	
	/**
	 * 管理员审核不通过操作
	 */
	void endReturn(ProContract proContract);
	
	/**
     * 钢筋班组首页：2、根据项目查询单项数据
     * @param params
     * @return
     */
	List<Map<String,Object>> getItemValueByPro(Map<String, Object> params);
	
	/**
	 * 根据劳务合同类型得到 整改率  跟 整改完成率
	 * @param params
	 * @return
	 */
	Map<String, Object> getItemValue(Map<String, Object> params);
	
	 Map<String, Object> getItemValueProperty(Map<String, Object> params);
	
}

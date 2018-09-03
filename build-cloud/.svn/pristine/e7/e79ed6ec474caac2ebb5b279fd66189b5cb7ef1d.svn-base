package com.build.cloud.modules.productplan.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.build.cloud.modules.bs.entity.BsRectificationEntity;
import com.build.cloud.modules.person.dto.WorkTeamInfo;
import com.build.cloud.modules.productplan.dto.ProContract;
import com.build.cloud.modules.productplan.dto.ProContractPlandetail;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liangsen
 * @since 2018-04-23
 */
public interface ProContractMapper extends BaseMapper<ProContract> {
	List<ProContract> selectConList(Page<ProContract> page, Map<String, Object> map);

	List<ProContract> getWorkOrderInfoByCon(List<String> contList);

	List<ProContract> getWorkRecordDaysByCon(List<String> contList);

	List<Map<String, Object>> getItemValueByPro(Map<String, Object> params);

	List<BsRectificationEntity> findBsRectification(Map<String, Object> params);

	HashMap<String, Object> getItemValue(Map<String, Object> params);
}
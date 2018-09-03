/**
 * 
 */
package com.build.cloud.modules.bs.dao;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.build.cloud.modules.bs.entity.BsMtrArchiveEntity;

/**
 * @className BsMtrArchiveDao
 * @descripion bsMtrArchive dao接口
 * @author WangJun
 * @date 2018年4月11日下午6:13:41
 */
public interface BsMtrArchiveDao extends BaseMapper<BsMtrArchiveEntity>{
	/**
	 * @param page
	 * @param params
	 * @return
	 */
	public List<BsMtrArchiveEntity> selectPageByKindId(Page<BsMtrArchiveEntity> page, Map<String, Object> params);

	/**
	 * @param map
	 * @return
	 */
	public List<BsMtrArchiveEntity> selectPageByKindId(Map<String, Object> map);
}

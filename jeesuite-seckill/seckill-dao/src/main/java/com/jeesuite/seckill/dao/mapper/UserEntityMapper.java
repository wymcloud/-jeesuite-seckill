package com.jeesuite.seckill.dao.mapper;

import java.util.List;
import com.jeesuite.seckill.dao.BaseMapper;
import com.jeesuite.seckill.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import com.jeesuite.mybatis.plugin.cache.annotation.Cache;

public interface UserEntityMapper extends BaseMapper<UserEntity> {
	
	@Cache
	public UserEntity findByMobile(@Param("mobile") String mobile);
	
	@Cache
	List<UserEntity> findByType(@Param("type") int type);
	
	@Cache
	@Select("SELECT count(*) FROM users where type=#{type}")
	@ResultMap("BaseResultMap")
    int countByType(@Param("type") int type);
	
	int updateType(@Param("ids") int[] ids,@Param("type") int type);

	@Cache
	List<Integer> findIdsByType(@Param("type") int type);
}
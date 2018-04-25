package com.eric.grace.upms.modules.sys.mapper;

import com.eric.grace.dao.common.dao.CommonDao;
import com.eric.grace.upms.modules.sys.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * TokenMapper:
 *
 * @author: MrServer
 * @since: 2018/4/20 上午11:31
 */
@Mapper
public interface TokenMapper extends CommonDao<TokenEntity> {


    /**
     * 根据userId 查询是否生成过token
     * @param userId
     * @return
     */
    TokenEntity selectByUserId(String userId);

    /**
     * 根据token获取token
     * @param token
     * @return
     */
    TokenEntity queryByToken(String token);
}

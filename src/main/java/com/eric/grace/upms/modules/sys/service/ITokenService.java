package com.eric.grace.upms.modules.sys.service;

import com.eric.grace.dao.common.service.ICommonService;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.upms.modules.sys.entity.TokenEntity;

/**
 * ITokenService:  生成token
 *
 * @author: MrServer
 * @since: 2018/4/20 上午11:28
 */
public interface ITokenService extends ICommonService<TokenEntity> {


    /**
     * 生成token
     * @param userId
     * @return
     */
    ResponseVo createToken(String userId);



}

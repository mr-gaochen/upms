package com.eric.grace.upms.service.impl;

import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.upms.common.constant.SysConstant;
import com.eric.grace.upms.common.utils.SpringContextHolder;
import com.eric.grace.upms.entity.TokenEntity;
import com.eric.grace.upms.mapper.TokenMapper;
import com.eric.grace.upms.service.ITokenService;
import com.eric.grace.utils.common.RandomUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TokenServiceImpl: token实现类
 *
 * @author: MrServer
 * @since: 2018/4/20 上午11:30
 */
@Service
public class TokenServiceImpl extends CommonServiceImpl<TokenMapper,TokenEntity> implements ITokenService {


    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public ResponseVo createToken(String userId) {

        //生成一个token
        String token = RandomUtil.simpleUUID();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);



        //判断是否生成过token
        TokenEntity tokenEntity = SpringContextHolder.getBean(TokenMapper.class).selectByUserId(userId);
        if(tokenEntity == null){
            tokenEntity = new TokenEntity();
            tokenEntity.setId(RandomUtil.simpleUUID());
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            tokenEntity.setDelFlag(SysConstant.DEFAULT_DEL_FLAG_NO);
            tokenEntity.setCreateTime(now);
            //保存token
            super.insert(tokenEntity);
        }else{
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //更新token
            updateById(tokenEntity);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", EXPIRE);

        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS,map);
    }
}
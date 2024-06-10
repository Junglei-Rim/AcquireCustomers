package edu.swpu.modules.portrait.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.Query;

import edu.swpu.modules.portrait.dao.UserProfileDao;
import edu.swpu.modules.portrait.entity.UserProfileEntity;
import edu.swpu.modules.portrait.service.UserProfileService;


@Service("userProfileService")
public class UserProfileServiceImpl extends ServiceImpl<UserProfileDao, UserProfileEntity> implements UserProfileService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserProfileEntity> page = this.page(
                new Query<UserProfileEntity>().getPage(params),
                new QueryWrapper<UserProfileEntity>().orderByDesc("id")//根据id倒序查询

        );

        return new PageUtils(page);
    }

}

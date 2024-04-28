package edu.swpu.modules.analytics.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.Query;

import edu.swpu.modules.analytics.dao.UserBehaviorStatsDao;
import edu.swpu.modules.analytics.entity.UserBehaviorStatsEntity;
import edu.swpu.modules.analytics.service.UserBehaviorStatsService;


@Service("userBehaviorStatsService")
public class UserBehaviorStatsServiceImpl extends ServiceImpl<UserBehaviorStatsDao, UserBehaviorStatsEntity> implements UserBehaviorStatsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserBehaviorStatsEntity> page = this.page(
                new Query<UserBehaviorStatsEntity>().getPage(params),
                new QueryWrapper<UserBehaviorStatsEntity>()
        );

        return new PageUtils(page);
    }

}
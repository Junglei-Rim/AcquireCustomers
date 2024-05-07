package edu.swpu.modules.analytics.service.impl;

import edu.swpu.modules.source.dao.DouyinAwemeDao;
import edu.swpu.modules.source.entity.DouyinAwemeEntity;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.Query;

import edu.swpu.modules.analytics.dao.HotTopicsDao;
import edu.swpu.modules.analytics.entity.HotTopicsEntity;
import edu.swpu.modules.analytics.service.HotTopicsService;


@Service("hotTopicsService")
public class HotTopicsServiceImpl extends ServiceImpl<HotTopicsDao, HotTopicsEntity> implements HotTopicsService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HotTopicsEntity> page = this.page(
                new Query<HotTopicsEntity>().getPage(params),
                new QueryWrapper<HotTopicsEntity>()
        );

        return new PageUtils(page);
    }

}

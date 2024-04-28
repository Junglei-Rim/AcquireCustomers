package edu.swpu.modules.promotion.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.Query;

import edu.swpu.modules.promotion.dao.PromotionPlanDao;
import edu.swpu.modules.promotion.entity.PromotionPlanEntity;
import edu.swpu.modules.promotion.service.PromotionPlanService;


@Service("promotionPlanService")
public class PromotionPlanServiceImpl extends ServiceImpl<PromotionPlanDao, PromotionPlanEntity> implements PromotionPlanService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PromotionPlanEntity> page = this.page(
                new Query<PromotionPlanEntity>().getPage(params),
                new QueryWrapper<PromotionPlanEntity>()
        );

        return new PageUtils(page);
    }

}
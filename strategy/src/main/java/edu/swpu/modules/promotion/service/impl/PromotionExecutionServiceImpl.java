package edu.swpu.modules.promotion.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.Query;

import edu.swpu.modules.promotion.dao.PromotionExecutionDao;
import edu.swpu.modules.promotion.entity.PromotionExecutionEntity;
import edu.swpu.modules.promotion.service.PromotionExecutionService;


@Service("promotionExecutionService")
public class PromotionExecutionServiceImpl extends ServiceImpl<PromotionExecutionDao, PromotionExecutionEntity> implements PromotionExecutionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PromotionExecutionEntity> page = this.page(
                new Query<PromotionExecutionEntity>().getPage(params),
                new QueryWrapper<PromotionExecutionEntity>()
        );

        return new PageUtils(page);
    }

}
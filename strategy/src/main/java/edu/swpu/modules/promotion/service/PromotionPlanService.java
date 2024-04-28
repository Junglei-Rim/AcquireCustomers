package edu.swpu.modules.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.modules.promotion.entity.PromotionPlanEntity;

import java.util.Map;

/**
 * 
 *
 * @author guozhenglei
 * @email guozhenglei7@gmail.com
 * @date 2024-04-28 17:51:43
 */
public interface PromotionPlanService extends IService<PromotionPlanEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


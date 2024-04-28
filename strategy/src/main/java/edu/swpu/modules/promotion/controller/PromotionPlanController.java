package edu.swpu.modules.promotion.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.swpu.modules.promotion.entity.PromotionPlanEntity;
import edu.swpu.modules.promotion.service.PromotionPlanService;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.R;



/**
 *
 *
 * @author guozhenglei
 * @email guozhenglei7@gmail.com
 * @date 2024-04-28 17:51:43
 */
@RestController
@RequestMapping("strategy/promotion/promotionplan")
public class PromotionPlanController {
    @Autowired
    private PromotionPlanService promotionPlanService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = promotionPlanService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		PromotionPlanEntity promotionPlan = promotionPlanService.getById(id);

        return R.ok().put("promotionPlan", promotionPlan);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PromotionPlanEntity promotionPlan){
		promotionPlanService.save(promotionPlan);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PromotionPlanEntity promotionPlan){
		promotionPlanService.updateById(promotionPlan);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		promotionPlanService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

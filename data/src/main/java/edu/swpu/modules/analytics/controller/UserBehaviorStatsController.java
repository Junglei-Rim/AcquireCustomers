package edu.swpu.modules.analytics.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.swpu.modules.analytics.entity.UserBehaviorStatsEntity;
import edu.swpu.modules.analytics.service.UserBehaviorStatsService;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.R;



/**
 *
 *
 * @author guozhenglei
 * @email guozhenglei7@gmail.com
 * @date 2024-04-28 16:20:43
 */
@RestController
@RequestMapping("data/analytics/userbehaviorstats")
public class UserBehaviorStatsController {
    @Autowired
    private UserBehaviorStatsService userBehaviorStatsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userBehaviorStatsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserBehaviorStatsEntity userBehaviorStats = userBehaviorStatsService.getById(id);

        return R.ok().put("userBehaviorStats", userBehaviorStats);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserBehaviorStatsEntity userBehaviorStats){
		userBehaviorStatsService.save(userBehaviorStats);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserBehaviorStatsEntity userBehaviorStats){
		userBehaviorStatsService.updateById(userBehaviorStats);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userBehaviorStatsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

package edu.swpu.modules.analytics.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.swpu.modules.analytics.entity.HotTopicsEntity;
import edu.swpu.modules.analytics.service.HotTopicsService;
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
@RequestMapping("data/analytics/hottopics")
public class HotTopicsController {
    @Autowired
    private HotTopicsService hotTopicsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = hotTopicsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		HotTopicsEntity hotTopics = hotTopicsService.getById(id);

        return R.ok().put("hotTopics", hotTopics);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody HotTopicsEntity hotTopics){
		hotTopicsService.save(hotTopics);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody HotTopicsEntity hotTopics){
		hotTopicsService.updateById(hotTopics);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		hotTopicsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

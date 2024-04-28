package edu.swpu.modules.source.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.swpu.modules.source.entity.DouyinAwemeEntity;
import edu.swpu.modules.source.service.DouyinAwemeService;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.R;



/**
 *
 *
 * @author guozhenglei
 * @email guozhenglei7@gmail.com
 * @date 2024-04-28 11:34:15
 */
@RestController
@RequestMapping("data/source/douyinaweme")
public class DouyinAwemeController {
    @Autowired
    private DouyinAwemeService douyinAwemeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = douyinAwemeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		DouyinAwemeEntity douyinAweme = douyinAwemeService.getById(id);

        return R.ok().put("douyinAweme", douyinAweme);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DouyinAwemeEntity douyinAweme){
		douyinAwemeService.save(douyinAweme);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DouyinAwemeEntity douyinAweme){
		douyinAwemeService.updateById(douyinAweme);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		douyinAwemeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

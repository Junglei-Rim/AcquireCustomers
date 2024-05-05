package edu.swpu.modules.source.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.swpu.modules.source.entity.DouyinAwemeCommentEntity;
import edu.swpu.modules.source.service.DouyinAwemeCommentService;
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
@RequestMapping("/data/douyinawemecomment")
public class DouyinAwemeCommentController {
    @Autowired
    private DouyinAwemeCommentService douyinAwemeCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = douyinAwemeCommentService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		DouyinAwemeCommentEntity douyinAwemeComment = douyinAwemeCommentService.getById(id);

        return R.ok().put("douyinAwemeComment", douyinAwemeComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DouyinAwemeCommentEntity douyinAwemeComment){
		douyinAwemeCommentService.save(douyinAwemeComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DouyinAwemeCommentEntity douyinAwemeComment){
		douyinAwemeCommentService.updateById(douyinAwemeComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		douyinAwemeCommentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

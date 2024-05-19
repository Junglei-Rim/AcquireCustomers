package edu.swpu.modules.portrait.controller;

import java.util.Arrays;
import java.util.Map;

import com.aliyuncs.exceptions.ClientException;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.R;
import edu.swpu.modules.portrait.aliyunNlp.EmotionAnalysis;
import edu.swpu.modules.portrait.feign.DataFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.swpu.modules.portrait.entity.UserProfileEntity;
import edu.swpu.modules.portrait.service.UserProfileService;


/**
 * @author guozhenglei
 * @email guozhenglei7@gmail.com
 * @date 2024-04-28 16:31:39
 */
@RestController
@RequestMapping("/customer/userprofile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private DataFeignService dataFeignService;

    @Autowired
    private EmotionAnalysis emotionAnalysis;

    /**
     * 远程调用模糊查询接口
     *
     * @return
     */
    @RequestMapping("/check")
    public R check() throws ClientException {
        R car = dataFeignService.getCommentsByContent("汽车");
        return car;
    }

    //测试情感处理接口
    @RequestMapping("/emotion")
    public R testEmotion(@RequestParam Map<String, String> params) throws ClientException {
        String comment = params.get("comment");
        return emotionAnalysis.getEmotional(comment);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userProfileService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        UserProfileEntity userProfile = userProfileService.getById(id);

        return R.ok().put("userProfile", userProfile);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserProfileEntity userProfile) {
        userProfileService.save(userProfile);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserProfileEntity userProfile) {
        userProfileService.updateById(userProfile);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        userProfileService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

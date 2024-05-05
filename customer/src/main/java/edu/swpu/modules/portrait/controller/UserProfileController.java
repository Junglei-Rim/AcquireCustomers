package edu.swpu.modules.portrait.controller;

import java.util.Arrays;
import java.util.Map;

import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.swpu.modules.portrait.entity.UserProfileEntity;
import edu.swpu.modules.portrait.service.UserProfileService;




/**
 *
 *
 * @author guozhenglei
 * @email guozhenglei7@gmail.com
 * @date 2024-04-28 16:31:39
 */
@RestController
@RequestMapping("/customer/userprofile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userProfileService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserProfileEntity userProfile = userProfileService.getById(id);

        return R.ok().put("userProfile", userProfile);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserProfileEntity userProfile){
		userProfileService.save(userProfile);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserProfileEntity userProfile){
		userProfileService.updateById(userProfile);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userProfileService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

package edu.swpu.modules.portrait.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.modules.portrait.entity.UserProfileEntity;

import java.util.Map;

/**
 * 
 *
 * @author guozhenglei
 * @email guozhenglei7@gmail.com
 * @date 2024-04-28 16:31:39
 */
public interface UserProfileService extends IService<UserProfileEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


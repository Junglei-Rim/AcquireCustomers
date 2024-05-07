package edu.swpu.modules.analytics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.modules.analytics.entity.HotTopicsEntity;

import java.util.Map;

/**
 *
 *
 * @author guozhenglei
 * @email guozhenglei7@gmail.com
 * @date 2024-04-28 16:20:43
 */
public interface HotTopicsService extends IService<HotTopicsEntity> {

    PageUtils queryPage(Map<String, Object> params);


}


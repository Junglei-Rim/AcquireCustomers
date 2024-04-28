package edu.swpu.modules.source.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.modules.source.entity.DouyinAwemeEntity;

import java.util.Map;

/**
 * 
 *
 * @author guozhenglei
 * @email guozhenglei7@gmail.com
 * @date 2024-04-28 11:34:15
 */
public interface DouyinAwemeService extends IService<DouyinAwemeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


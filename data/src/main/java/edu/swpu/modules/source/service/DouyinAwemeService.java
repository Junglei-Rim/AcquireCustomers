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

    /**
     * 根据添加时间戳addTs，查询一分钟以内新增数据的记录数
     * @return
     */
    int countNewRecordsWithinOneMinute();
}


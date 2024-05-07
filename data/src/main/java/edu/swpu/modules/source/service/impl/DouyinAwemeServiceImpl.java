package edu.swpu.modules.source.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.Query;

import edu.swpu.modules.source.dao.DouyinAwemeDao;
import edu.swpu.modules.source.entity.DouyinAwemeEntity;
import edu.swpu.modules.source.service.DouyinAwemeService;


@Service("douyinAwemeService")
public class DouyinAwemeServiceImpl extends ServiceImpl<DouyinAwemeDao, DouyinAwemeEntity> implements DouyinAwemeService {

    @Autowired
    private DouyinAwemeDao douyinAwemeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DouyinAwemeEntity> page = this.page(
                new Query<DouyinAwemeEntity>().getPage(params),
                new QueryWrapper<DouyinAwemeEntity>().orderByDesc("add_ts") // 根据时间戳倒序排序
        );

        return new PageUtils(page);
    }

    @Override
    public int countNewRecordsWithinOneMinute() {
        // 获取当前时间戳（毫秒）
        long currentTimestamp = System.currentTimeMillis();

        // 计算一分钟前的时间戳
        long oneMinuteAgoTimestamp = currentTimestamp - (60 * 1000);

        // 构建查询条件
        QueryWrapper<DouyinAwemeEntity> wrapper = new QueryWrapper<>();
        wrapper.ge("add_ts", oneMinuteAgoTimestamp); // 添加时间大于等于一分钟前的时间戳
        wrapper.le("add_ts", currentTimestamp); // 添加时间小于等于当前时间戳

        // 查询一分钟内新增的记录数
        return douyinAwemeDao.selectCount(wrapper);
    }

}

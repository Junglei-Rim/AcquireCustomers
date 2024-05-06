package edu.swpu.modules.source.service.impl;

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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DouyinAwemeEntity> page = this.page(
                new Query<DouyinAwemeEntity>().getPage(params),
                new QueryWrapper<DouyinAwemeEntity>().orderByDesc("add_ts") // 根据时间戳倒序排序
        );

        return new PageUtils(page);
    }

}

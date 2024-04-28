package edu.swpu.modules.source.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.Query;

import edu.swpu.modules.source.dao.DouyinAwemeCommentDao;
import edu.swpu.modules.source.entity.DouyinAwemeCommentEntity;
import edu.swpu.modules.source.service.DouyinAwemeCommentService;


@Service("douyinAwemeCommentService")
public class DouyinAwemeCommentServiceImpl extends ServiceImpl<DouyinAwemeCommentDao, DouyinAwemeCommentEntity> implements DouyinAwemeCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DouyinAwemeCommentEntity> page = this.page(
                new Query<DouyinAwemeCommentEntity>().getPage(params),
                new QueryWrapper<DouyinAwemeCommentEntity>()
        );

        return new PageUtils(page);
    }

}
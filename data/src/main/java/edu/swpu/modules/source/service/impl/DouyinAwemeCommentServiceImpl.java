package edu.swpu.modules.source.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.swpu.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.swpu.common.utils.PageUtils;

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

/*        // 从 params 中获取页码和每页条数
        long curPage = Long.parseLong(String.valueOf(params.get("page")));
        long limit = Long.parseLong(String.valueOf(params.get("limit")));

        // 构建分页对象
        Page<DouyinAwemeCommentEntity> page = new Page<>(curPage, limit);

        // 执行分页查询
        baseMapper.selectPage(page, null);*/

        return new PageUtils(page);
    }

}

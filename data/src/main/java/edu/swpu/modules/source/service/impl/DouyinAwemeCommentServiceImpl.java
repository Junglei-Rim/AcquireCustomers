package edu.swpu.modules.source.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.swpu.common.utils.Query;
import edu.swpu.modules.source.entity.DouyinAwemeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private DouyinAwemeCommentDao douyinAwemeCommentDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<DouyinAwemeCommentEntity> page = this.page(
                new Query<DouyinAwemeCommentEntity>().getPage(params),
                new QueryWrapper<DouyinAwemeCommentEntity>().orderByDesc("add_ts") // 根据时间戳倒序排序
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

    @Override
    public int countNewRecordsWithinOneMinute() {
        // 获取当前时间戳（毫秒）
        long currentTimestamp = System.currentTimeMillis();

        // 计算一分钟前的时间戳
        long oneMinuteAgoTimestamp = currentTimestamp - (60 * 1000);

        // 构建查询条件
        QueryWrapper<DouyinAwemeCommentEntity> wrapper = new QueryWrapper<>();
        wrapper.ge("add_ts", oneMinuteAgoTimestamp); // 添加时间大于等于一分钟前的时间戳
        wrapper.le("add_ts", currentTimestamp); // 添加时间小于等于当前时间戳

        // 查询一分钟内新增的记录数
        return douyinAwemeCommentDao.selectCount(wrapper);
    }

    @Override
    public List<DouyinAwemeCommentEntity> findCommentsByContent(String content) {
        QueryWrapper<DouyinAwemeCommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("content", content);
        return douyinAwemeCommentDao.selectList(queryWrapper);

    }

}

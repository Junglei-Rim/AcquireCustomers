package edu.swpu.modules.analytics.service.impl;

import edu.swpu.modules.source.dao.DouyinAwemeDao;
import edu.swpu.modules.source.entity.DouyinAwemeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.Query;

import edu.swpu.modules.analytics.dao.UserBehaviorStatsDao;
import edu.swpu.modules.analytics.entity.UserBehaviorStatsEntity;
import edu.swpu.modules.analytics.service.UserBehaviorStatsService;


@Service("userBehaviorStatsService")
public class UserBehaviorStatsServiceImpl extends ServiceImpl<UserBehaviorStatsDao, UserBehaviorStatsEntity> implements UserBehaviorStatsService {

    @Autowired
    private DouyinAwemeDao douyinAwemeDao;

    @Autowired
    private UserBehaviorStatsDao userBehaviorStatsDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserBehaviorStatsEntity> page = this.page(
                new Query<UserBehaviorStatsEntity>().getPage(params),
                new QueryWrapper<UserBehaviorStatsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void autoInsert() {
        // 获取当前时间戳（毫秒）
        long currentTimestamp = System.currentTimeMillis();

        // 计算一分钟前的时间戳
        long oneMinuteAgoTimestamp = currentTimestamp - (60 * 1000);

        // 构建查询条件
        QueryWrapper<DouyinAwemeEntity> wrapper = new QueryWrapper<>();
        wrapper.ge("add_ts", oneMinuteAgoTimestamp); // 添加时间大于等于一分钟前的时间戳
        wrapper.le("add_ts", currentTimestamp); // 添加时间小于等于当前时间戳
        //在过去一分钟增加的创作者集合
        List<DouyinAwemeEntity> douyinAwemeEntities = douyinAwemeDao.selectList(wrapper);
        HashMap<String, Integer> countUsers = new HashMap<>();
        //插入
        for (int i = 0; i < douyinAwemeEntities.size(); i++) {

            DouyinAwemeEntity douyinAwemeEntity = douyinAwemeEntities.get(i);
            String userId = douyinAwemeEntity.getUserId();
            if (countUsers.get(userId) > 0) {
                //已存在，更新字段即可
                QueryWrapper<UserBehaviorStatsEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_id",userId);
                UserBehaviorStatsEntity old = userBehaviorStatsDao.selectOne(queryWrapper);
                old.setTotalLikes(Integer.valueOf(old.getTotalLikes()+douyinAwemeEntity.getLikedCount()));//更新点赞
                old.setTotalComments(Integer.valueOf(old.getTotalComments()+douyinAwemeEntity.getCommentCount()));//更新评论
                old.setTotalShares(Integer.valueOf(old.getTotalShares()+douyinAwemeEntity.getShareCount()));//更新分享
                old.setTotalVideos(old.getTotalVideos()+1);//更新分享
                old.setLastInteractionTs(currentTimestamp);//更新时间戳
            } else {
                countUsers.put(userId, countUsers.getOrDefault(userId, 0) + 1);//添加到map
                //不存在，做插入操作
                UserBehaviorStatsEntity userBehaviorStatsEntity = new UserBehaviorStatsEntity();
                userBehaviorStatsEntity.setUserId(douyinAwemeEntity.getShortUserId());//插入用户id
                userBehaviorStatsEntity.setTotalLikes(Integer.valueOf(douyinAwemeEntity.getLikedCount()));//点赞数
                userBehaviorStatsEntity.setTotalComments(Integer.valueOf(douyinAwemeEntity.getCommentCount()));//评论数
                userBehaviorStatsEntity.setTotalShares(Integer.valueOf(douyinAwemeEntity.getShareCount()));//分享数
                userBehaviorStatsEntity.setTotalVideos(1);
                userBehaviorStatsEntity.setTotalCommentsOnOwnVideos(100);
                userBehaviorStatsEntity.setTotalCommentsOnOtherVideos(200);
                userBehaviorStatsEntity.setLastInteractionTs(currentTimestamp);
                //插入
                userBehaviorStatsDao.insert(userBehaviorStatsEntity);
            }

        }

    }

}

package edu.swpu.modules.portrait.controller;

import java.util.*;

import com.aliyuncs.exceptions.ClientException;
import edu.swpu.common.utils.PageUtils;
import edu.swpu.common.utils.R;
import edu.swpu.modules.portrait.aliyunNlp.ChineseWordSegmentation;
import edu.swpu.modules.portrait.aliyunNlp.EmotionAnalysis;
import edu.swpu.modules.portrait.aliyunNlp.HeadwordExtraction;
import edu.swpu.modules.portrait.entity.Gender;
import edu.swpu.modules.portrait.feign.DataFeignService;
import edu.swpu.modules.source.entity.DouyinAwemeCommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.swpu.modules.portrait.entity.UserProfileEntity;
import edu.swpu.modules.portrait.service.UserProfileService;


/**
 * @author guozhenglei
 * @email guozhenglei7@gmail.com
 * @date 2024-04-28 16:31:39
 */
@RestController
@RequestMapping("/customer/userprofile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private DataFeignService dataFeignService;

    @Autowired
    private EmotionAnalysis emotionAnalysis;

    @Autowired
    private HeadwordExtraction headwordExtraction;

    @Autowired
    private ChineseWordSegmentation chineseWordSegmentation;

    /**
     * 远程调用模糊查询接口
     *
     * @return
     */
    @RequestMapping("/check")
    public R check(@RequestParam String content) throws ClientException {
        List<DouyinAwemeCommentEntity> list = dataFeignService.getCommentsByContent(content);
        return R.ok().put("data", list);
    }

    //测试情感处理接口
    @RequestMapping("/emotion")
    public R testEmotion(@RequestParam Map<String, String> params) throws ClientException {
        String comment = params.get("comment");
        return R.ok().put("data", emotionAnalysis.getEmotional(comment));
    }

    //中心词提取接口测试
    @RequestMapping("/getKeyword")
    public R testGetKeyword(@RequestParam Map<String, String> params) throws ClientException {
        String keyword = params.get("keyword");
        return headwordExtraction.getHeadword(keyword);
    }

    //测试中文分词
    @RequestMapping("/getWordS")
    public R testGetWords(@RequestParam Map<String, String> params) throws ClientException {
        String comment = params.get("comment");
        return R.ok().put("data", chineseWordSegmentation.getWords(comment));
    }

    /**
     * 客户识别接口：
     * 1.前端提供输入框，用户输入检索关键词
     * 2.根据关键词对评论信息进行模糊查询
     * 3.对查询出的评论进行情感分析：正面评论，负面评论，中性评论
     * 4.对筛选出的评论提取中心词作为画像的兴趣标签
     * 5.插入画像数据逻辑：用户id唯一，不能重复添加用户画像
     */
    @RequestMapping("/getCustomer")
    public R CustomerIdentification(@RequestParam Map<String, String> params) throws ClientException {
        //1.前端提供输入框，用户输入检索关键词
        String keyword = params.get("keyword");
        //2.根据关键词对评论信息进行模糊查询
        List<DouyinAwemeCommentEntity> comments = dataFeignService.getCommentsByContent(keyword);
        //3.对查询出的评论进行情感分析：正面评论，负面评论，中性评论
        int mark = 0;
        int cnt = 0;
        for (DouyinAwemeCommentEntity comment : comments) {
            String flag = emotionAnalysis.getEmotional(comment.getContent());
            //4.对筛选出的评论提取中心词作为画像的兴趣标签
            String words = chineseWordSegmentation.getWords(comment.getContent());
            UserProfileEntity userProfile = new UserProfileEntity();
            if (flag.equals("正面")) {
                userProfile.setGender(Gender.male);
            } else if (flag.equals("中性")) {
                userProfile.setGender(Gender.female);
            } else {
                continue;
            }
            userProfile.setInterestTags(words);
            //5.插入画像数据逻辑：用户id唯一，不能重复添加用户画像
            userProfile.setUserId(comment.getUserId());
            userProfile.setBio(comment.getUserSignature());
            userProfile.setNickname(comment.getNickname());
            userProfile.setAvatar(comment.getAvatar());
            Random random = new Random();
            int ageRandom = random.nextInt(15, 60);
            userProfile.setAge(ageRandom);
            userProfile.setLocation(comment.getIpLocation());
            boolean save = userProfileService.save(userProfile);
            if (save) {
                cnt++;
            }
        }
        if (cnt > 0) {
            mark = 1;
        }
        return mark == 1 ? R.ok("获取客户成功！本次共获取用户数量："+cnt) : R.error("获取失败，请爬取之后重试！");
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userProfileService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        UserProfileEntity userProfile = userProfileService.getById(id);

        return R.ok().put("userProfile", userProfile);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserProfileEntity userProfile) {
        userProfileService.save(userProfile);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserProfileEntity userProfile) {
        userProfileService.updateById(userProfile);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        userProfileService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

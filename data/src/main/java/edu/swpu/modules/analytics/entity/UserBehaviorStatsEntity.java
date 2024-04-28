package edu.swpu.modules.analytics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author guozhenglei
 * @email guozhenglei7@gmail.com
 * @date 2024-04-28 16:20:43
 */
@Data
@TableName("user_behavior_stats")
public class UserBehaviorStatsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String userId;
	/**
	 * 
	 */
	private Integer totalLikes;
	/**
	 * 
	 */
	private Integer totalComments;
	/**
	 * 
	 */
	private Integer totalShares;
	/**
	 * 
	 */
	private Integer totalVideos;
	/**
	 * 
	 */
	private Integer totalCommentsOnOwnVideos;
	/**
	 * 
	 */
	private Integer totalCommentsOnOtherVideos;
	/**
	 * 
	 */
	private Long lastInteractionTs;

}

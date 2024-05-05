package edu.swpu.modules.source.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @date 2024-04-28 11:34:15
 */
@Data
@TableName("douyin_aweme")
public class DouyinAwemeEntity implements Serializable {
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
	private String secUid;
	/**
	 *
	 */
	private String shortUserId;
	/**
	 *
	 */
	private String userUniqueId;
	/**
	 *
	 */
	private String nickname;
	/**
	 *
	 */
	private String avatar;
	/**
	 *
	 */
	private String userSignature;
	/**
	 *
	 */
	private String ipLocation;
	/**
	 *
	 */
	private Long addTs;
	/**
	 *
	 */
	private Long lastModifyTs;
	/**
	 *
	 */
	private String awemeId;
	/**
	 *
	 */
	private String awemeType;
	/**
	 *
	 */
	private String title;
	/**
	 *
	 */
	@TableField(value = "`desc`")
	private String desc;
	/**
	 *
	 */
	private Long createTime;
	/**
	 *
	 */
	private String likedCount;
	/**
	 *
	 */
	private String commentCount;
	/**
	 *
	 */
	private String shareCount;
	/**
	 *
	 */
	private String collectedCount;
	/**
	 *
	 */
	private String awemeUrl;

}

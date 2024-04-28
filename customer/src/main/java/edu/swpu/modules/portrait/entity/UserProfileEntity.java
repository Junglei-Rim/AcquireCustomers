package edu.swpu.modules.portrait.entity;

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
 * @date 2024-04-28 16:31:39
 */
@Data
@TableName("user_profile")
public class UserProfileEntity implements Serializable {
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
	private String nickname;
	/**
	 *
	 */
	private String avatar;
	/**
	 *
	 */
	private Gender  gender;
	/**
	 *
	 */
	private Integer age;
	/**
	 *
	 */
	private String location;
	/**
	 *
	 */
	private String bio;
	/**
	 *
	 */
	private String interestTags;

}



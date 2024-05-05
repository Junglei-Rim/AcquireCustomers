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
@TableName("hot_topics")
public class HotTopicsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 热词id
	 */
	@TableId
	private Integer id;
	/**
	 *
	 */
	private String topic;
	/**
	 *
	 */
	private Integer videoCount;
	/**
	 *
	 */
	private Integer commentCount;
	/**
	 *
	 */
	private Long lastUpdateTs;

}

package edu.swpu.modules.promotion.entity;

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
 * @date 2024-04-28 17:51:43
 */
@Data
@TableName("promotion_execution")
public class PromotionExecutionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer planId;
	/**
	 * 
	 */
	private String userId;
	/**
	 * 
	 */
	private Long executionTs;
	/**
	 * 
	 */
	private String result;

}

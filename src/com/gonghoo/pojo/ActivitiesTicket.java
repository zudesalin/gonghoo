/**
 * 
 */
package com.gonghoo.pojo;
import java.util.Date;

/**
 * 活动票务
 * 
 * @author gavinshao
 *
 */

public class ActivitiesTicket  {
	private Integer id;
	// 活动id
	private Integer activitiesId;
	// 活动标题（不创建数据库字段）
	private String activitiesTitle;
	// 票种
	private String type;
	
	// 票数量
	private Integer ticketNum;
	//剩余票数量
	private Integer surplusNum;
	// 价格
	private Double price;
	// 有效日期开始日期
	private Date startDate;
	// 有效日期结束日期
	private Date endDate;
	//允许最大人数
	private  Integer  maxNumber;
	
	private String memo;
	//席位
	private String seat;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivitiesId() {
		return activitiesId;
	}

	public void setActivitiesId(Integer activitiesId) {
		this.activitiesId = activitiesId;
	}

	public String getActivitiesTitle() {
		return activitiesTitle;
	}

	public void setActivitiesTitle(String activitiesTitle) {
		this.activitiesTitle = activitiesTitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(Integer ticketNum) {
		this.ticketNum = ticketNum;
	}

	public Integer getSurplusNum() {
		return surplusNum;
	}

	public void setSurplusNum(Integer surplusNum) {
		this.surplusNum = surplusNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}
}

package com.fullstack.training;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="description")
    private String description;

	@Column(name="status")
    private String status;
	
    @Column(name="progress")
    private Integer progress = 0;
	
    @Column(name="fees")
    private Float fees = 0.0f;
	
    @Column(name="commission_amount")
    private Float commissionAmount = 0.0f;
	
	@Column(name="rating")
    private Integer rating = 0;
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	@Column(name="start_date")
    private String startDate;
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	@Column(name="end_date")
    private String endDate;
	
	@JsonFormat(pattern = "HH:mm:ss")
	@Column(name="start_time")
    private String startTime;
	
	@JsonFormat(pattern = "HH:mm:ss")
	@Column(name="end_time")
    private String endTime;
	
	@Column(name="amount_received")
    private Float amountReceived = 0.0f;
	
	@Column(name="user_id")
    private int userId;
	
	@Column(name="mentor_id")
    private int mentorId;
	
	@Column(name="skill_id")
    private int skillId;
	
	@Column(name="razorpay_payment_id")
    private String razorpayPaymentId;
	
	@Column(name="training_url")
    private String trainingUrl;
   

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Integer getProgress() {
			return progress;
		}

		public void setProgress(Integer progress) {
			this.progress = progress;
		}

		public Float getFees() {
			return fees;
		}

		public void setFees(Float fees) {
			this.fees = fees;
		}

		public Integer getRating() {
			return rating;
		}

		public void setRating(Integer rating) {
			this.rating = rating;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public Float getAmountReceived() {
			return amountReceived;
		}

		public void setAmountReceived(Float amountReceived) {
			this.amountReceived = amountReceived;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getMentorId() {
			return mentorId;
		}

		public void setMentorId(int mentorId) {
			this.mentorId = mentorId;
		}

		public int getSkillId() {
			return skillId;
		}

		public void setSkillId(int skillId) {
			this.skillId = skillId;
		}

		public String getRazorpayPaymentId() {
			return razorpayPaymentId;
		}

		public void setRazorpayPaymentId(String razorpayPaymentId) {
			this.razorpayPaymentId = razorpayPaymentId;
		}
		
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		public String getTrainingUrl() {
			return trainingUrl;
		}

		public void setTrainingUrl(String trainingUrl) {
			this.trainingUrl = trainingUrl;
		}
		
		 public Float getCommissionAmount() {
				return commissionAmount;
			}

			public void setCommissionAmount(Float commissionAmount) {
				this.commissionAmount = commissionAmount;
			}

		
}
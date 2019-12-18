package com.fullstack.mentor;

import javax.persistence.*;

@Entity
@Table(name = "mentor")
public class Mentor {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
	@Column
    private int yearsOfExperience;
	@Column
    private int trainingsDeliveredInTotal;
	@Column
    private String specificTechnology;
	@Column
	private double feeCharged;
    @Column
    private String username;
	@Column
    private String password;
	@Column
	private String token;
	

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public int getTrainingsDeliveredInTotal() {
		return trainingsDeliveredInTotal;
	}

	public void setTrainingsDeliveredInTotal(int trainingsDeliveredInTotal) {
		this.trainingsDeliveredInTotal = trainingsDeliveredInTotal;
	}

	public String getSpecificTechnology() {
		return specificTechnology;
	}

	public void setSpecificTechnology(String specificTechnology) {
		this.specificTechnology = specificTechnology;
	}

	public double getFeeCharged() {
		return feeCharged;
	}

	public void setFeeCharged(double feeCharged) {
		this.feeCharged = feeCharged;
	}
}

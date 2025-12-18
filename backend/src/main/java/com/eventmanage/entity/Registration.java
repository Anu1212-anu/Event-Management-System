package com.eventmanage.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;



    
    private LocalDate registeredDate = LocalDate.now();
  

    
    
    
    private String registrationDate; // yyyy-MM-dd
    private String status; // CONFIRMED / CANCELLED
   private String selectedEventDate;   // NEW FIELD
   


    
	public String getSelectedEventDate() {
		return selectedEventDate;
	}
	public void setSelectedEventDate(String selectedEventDate) {
		this.selectedEventDate = selectedEventDate;
	}
	public Long getRegId() {
		return regId;
	}
	public void setRegId(Long regId) {
		this.regId = regId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Event getEvent() {
		return event;
	}
	public LocalDate getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(LocalDate registeredDate) {
		this.registeredDate = registeredDate;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Registration [regId=" + regId + ", user=" + user + ", event=" + event + ", registeredDate="
				+ registeredDate + ", registrationDate=" + registrationDate + ", status=" + status
				+ ", selectedEventDate=" + selectedEventDate + "]";
	}
    
    
}

package com.eventmanage.service;


import java.time.LocalDate;
import java.util.List;

import com.eventmanage.entity.Registration;

public interface RegistrationService {
	 Registration register(Long userId, Long eventId);
	 Registration registerEvent(Long userId, Long eventId, LocalDate selectedDate);
	 List<Registration> getAllRegistrations();
	 List<Registration> getBookingsByUser(Long userId);
	
	 
	 void cancelBooking(Long regId);

	
	 
	 


}

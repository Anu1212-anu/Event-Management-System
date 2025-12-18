package com.eventmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventmanage.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
}

package com.nkydev.config;

import com.nkydev.repository.EventRepository;
import org.springframework.stereotype.Component;

@Component("eventSecurity")
public class EventSecurity {

    private final EventRepository eventRepository;

    public EventSecurity(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public boolean isOrganizer(Integer eventId, String userEmail) {
        return eventRepository.findById(eventId)
                .map(event -> event.getOrganizer() != null &&
                        event.getOrganizer().getEmail().equalsIgnoreCase(userEmail))
                .orElse(false);
    }
}
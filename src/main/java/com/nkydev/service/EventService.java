package com.nkydev.service;

import com.nkydev.entity.Category;
import com.nkydev.entity.Event;
import com.nkydev.repository.CategoryRepository;
import com.nkydev.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    public EventService(EventRepository eventRepository, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event) {
        Category category = categoryRepository.findById(event.getCategory().getId())
                .orElseThrow(() -> new IllegalStateException("category not found"));

        event.setCategory(category);
        return eventRepository.save(event);
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("event not found with ID: " + id));
    }

    @Transactional
    public void updateEvent(Integer id, Event eventDetails) {
        Event event = eventRepository
                .findById(id).orElseThrow(() -> new IllegalStateException("event not found with ID: " + id));

        event.setName(eventDetails.getName());
        event.setDescription(eventDetails.getDescription());
        event.setDate(eventDetails.getDate());
        event.setLocation(eventDetails.getLocation());
        event.setCapacity(eventDetails.getCapacity());
        event.setCategory(eventDetails.getCategory());
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }
}

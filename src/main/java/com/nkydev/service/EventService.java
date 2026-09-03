package com.nkydev.service;

import com.nkydev.dto.category.CategoryResponseDTO;
import com.nkydev.dto.event.EventRequestDTO;
import com.nkydev.dto.event.EventResponseDTO;
import com.nkydev.entity.Category;
import com.nkydev.entity.Event;
import com.nkydev.entity.User;
import com.nkydev.repository.CategoryRepository;
import com.nkydev.repository.EventRepository;
import com.nkydev.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public EventResponseDTO createEvent(EventRequestDTO request) {
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new IllegalStateException("category not found"));

        // se obtien el mail del usuario autenticado x la via JWT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();

        User organizer = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new IllegalStateException("organizer user not found with email: " + currentEmail));

        Event event = new Event();
        event.setName(request.name());
        event.setDescription(request.description());
        event.setDate(request.date());
        event.setLocation(request.location());
        event.setCapacity(request.capacity());
        event.setCategory(category);
        event.setOrganizer(organizer);

        Event savedEvent = eventRepository.save(event);
        return mapToResponseDTO(savedEvent);
    }

    public EventResponseDTO getEventById(Integer id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("event not found with ID: " + id));

        return mapToResponseDTO(event);
    }

    @Transactional
    public EventResponseDTO updateEvent(Integer id, EventRequestDTO request) {
        Event event = eventRepository
                .findById(id).orElseThrow(() -> new IllegalStateException("event not found with ID: " + id));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new IllegalStateException("category not found with ID: " + request.categoryId()));

        event.setName(request.name());
        event.setDescription(request.description());
        event.setDate(request.date());
        event.setLocation(request.location());
        event.setCapacity(request.capacity());
        event.setCategory(category);

        return mapToResponseDTO(event);
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }

    public EventResponseDTO mapToResponseDTO(Event event){
        CategoryResponseDTO categoryDTO = new CategoryResponseDTO(
                event.getCategory().getId(),
                event.getCategory().getName()
        );

        return new EventResponseDTO(event.getId(),
                                    event.getName(),
                                    event.getDescription(),
                                    event.getDate(),
                                    event.getLocation(),
                                    event.getCapacity(),
                                    categoryDTO);
    }

    public List<EventResponseDTO> getEventsByCategoryId(Integer categoryId) {
        return eventRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }
}

package com.nkydev.service;

import com.nkydev.dto.category.CategoryResponseDTO;
import com.nkydev.dto.event.EventResponseDTO;
import com.nkydev.dto.tickettype.TicketTypeRequestDTO;
import com.nkydev.dto.tickettype.TicketTypeResponseDTO;
import com.nkydev.entity.Event;
import com.nkydev.entity.TicketType;
import com.nkydev.repository.EventRepository;
import com.nkydev.repository.TicketTypeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    public TicketTypeService(TicketTypeRepository ticketTypeRepository, EventRepository eventRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
        this.eventRepository = eventRepository;
    }

    public TicketTypeResponseDTO createTicketType(@Valid TicketTypeRequestDTO request) {
        Event event = eventRepository.findById(request.idEvent())
                .orElseThrow(() -> new IllegalStateException("event not found"));

        TicketType ticketType = new TicketType();
        ticketType.setName(request.name());
        ticketType.setDescription(request.description());
        ticketType.setPrice(request.price());
        ticketType.setQuantity(request.quantity());
        ticketType.setAvailableQuantity(request.quantity());
        ticketType.setEvent(event);

        TicketType savedTicketType = ticketTypeRepository.save(ticketType);
        return mapToResponseDTO(savedTicketType);
    }

    public List<TicketTypeResponseDTO> getAllTicketsType() {
        return ticketTypeRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public TicketTypeResponseDTO getTicketTypeById(Integer id) {
        TicketType ticketType = ticketTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("ticket type not found by ID: " + id));

        return mapToResponseDTO(ticketType);
    }

    @Transactional
    public TicketTypeResponseDTO updateTicketType(Integer id, TicketTypeRequestDTO request) {
        TicketType ticketType= ticketTypeRepository
                .findById(id).orElseThrow(() -> new IllegalStateException("ticket type not found by ID: " + id));

        Event event = eventRepository.findById(request.idEvent())
                .orElseThrow(() -> new IllegalStateException("event not found with ID: " + request.idEvent()));

        ticketType.setName(request.name());
        ticketType.setDescription(request.description());
        ticketType.setPrice(request.price());
        ticketType.setQuantity(request.quantity());
        ticketType.setAvailableQuantity(request.quantity());
        ticketType.setEvent(event);

        TicketType savedTicketType = ticketTypeRepository.save(ticketType);
        return mapToResponseDTO(savedTicketType);
    }

    public void deleteTicketType(Integer id) {
        ticketTypeRepository.deleteById(id);
    }

    public TicketTypeResponseDTO mapToResponseDTO(TicketType ticketType) {
        CategoryResponseDTO categoryDTO = new CategoryResponseDTO(
                ticketType.getEvent().getCategory().getId(),
                ticketType.getEvent().getCategory().getName()
        );

        EventResponseDTO eventResponseDTO = new EventResponseDTO(
                ticketType.getEvent().getId(),
                ticketType.getEvent().getName(),
                ticketType.getEvent().getDescription(),
                ticketType.getEvent().getDate(),
                ticketType.getEvent().getLocation(),
                ticketType.getEvent().getCapacity(),
                categoryDTO
        );

        return new TicketTypeResponseDTO(
                ticketType.getId(),
                ticketType.getName(),
                ticketType.getDescription(),
                ticketType.getPrice(),
                ticketType.getQuantity(),
                ticketType.getAvailableQuantity(),
                ticketType.getEvent().getId()
        );
    }
}

package com.nkydev.service;

import com.nkydev.dto.category.CategoryResponseDTO;
import com.nkydev.dto.event.EventResponseDTO;
import com.nkydev.dto.tickettype.TicketTypeRequestDTO;
import com.nkydev.dto.tickettype.TicketTypeResponseDTO;
import com.nkydev.entity.TicketType;
import com.nkydev.repository.TicketTypeRepository;
import jakarta.validation.Valid;

import java.util.List;

public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;

    public TicketTypeService(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    public TicketTypeResponseDTO createTicketType(@Valid TicketTypeRequestDTO request) {

    }

    public List<TicketTypeResponseDTO> getAllTicketsType(TicketTypeRequestDTO request) {
        return ticketTypeRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public TicketTypeResponseDTO getTicketTypeById(Integer id) {
    }

    public TicketTypeResponseDTO updateTicketType(Integer id, @Valid TicketTypeRequestDTO request) {
    }

    public void deleteTicketType(Integer id) {
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
                eventResponseDTO
        );
    }
}

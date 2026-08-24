package com.nkydev.dto.event;

import com.nkydev.entity.Category;

import java.time.LocalDateTime;

public record EventRequestDTO(String name,
                              String description,
                              LocalDateTime date,
                              String location,
                              Integer capacity,
                              Integer categoryId) {}

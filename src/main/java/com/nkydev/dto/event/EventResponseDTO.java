package com.nkydev.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nkydev.dto.category.CategoryResponseDTO;

import java.time.LocalDateTime;

public record EventResponseDTO(Integer id,
                               String name,
                               String description,

                               @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                               LocalDateTime date,
                               String location,
                               Integer capacity,
                               CategoryResponseDTO categoryId) {}

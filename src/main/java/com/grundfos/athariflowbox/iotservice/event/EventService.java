package com.grundfos.athariflowbox.iotservice.event;

import com.google.gson.Gson;
import com.grundfos.athariflowbox.iotservice.dtos.EventTypeDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record EventService(
        EventRepository eventRepository
) {
    public Event createFromEventDto(EventTypeDto dto) {
        var data = new Gson().toJson(dto.getData());
        var event = Event.builder()
                .id(UUID.randomUUID().toString())
                .serialNumber(dto.getSerialNumber())
                .type(dto.getType())
                .data(data)
                .build();

        return eventRepository.save(event);
    }
}

package com.grundfos.athariflowbox.iotservice.event;

import com.grundfos.athariflowbox.iotservice.common.ApiResponse;
import com.grundfos.athariflowbox.iotservice.domain.CardScannedEventDomain;
import com.grundfos.athariflowbox.iotservice.domain.DeviceInitializationEventDomain;
import com.grundfos.athariflowbox.iotservice.domain.TimeStampEventDomain;
import com.grundfos.athariflowbox.iotservice.domain.PowerEventDomain;
import com.grundfos.athariflowbox.iotservice.dtos.EventTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class HttpController {
    private final EventService eventService;

    @PostMapping("/power")
    public ResponseEntity<ApiResponse<PowerEventDomain>> saveEvent(@RequestBody EventTypeDto dto) {
        var event = eventService.createFromEventDto(dto);
        var domain = PowerEventDomain.mapEntityToDomain(event);
        var apiResponse = new ApiResponse<PowerEventDomain>();
        apiResponse.setSuccess(Boolean.TRUE);
        apiResponse.setData(domain);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/initialization")
    public ResponseEntity<ApiResponse<DeviceInitializationEventDomain>> handleDeviceInitialization(@RequestBody EventTypeDto dto) {
        var apiResponse = new ApiResponse<DeviceInitializationEventDomain>();
        var event = eventService.createFromEventDto(dto);
        var domain = DeviceInitializationEventDomain.mapEntityToDomain(event);
        apiResponse.setSuccess(Boolean.TRUE);
        apiResponse.setData(domain);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/card/scanned")
    public ResponseEntity<ApiResponse<CardScannedEventDomain>> handleCardScanned(@RequestBody EventTypeDto dto) {
        var event = eventService.createFromEventDto(dto);
        var domain = CardScannedEventDomain.mapEntityToDomain(event);
        var apiResponse = new ApiResponse<CardScannedEventDomain>();
        apiResponse.setData(domain);
        apiResponse.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/start_fetch")
    public ResponseEntity<ApiResponse<TimeStampEventDomain>> handleStartFetch(@RequestBody EventTypeDto dto) {
        var event = eventService.createFromEventDto(dto);
        var domain = TimeStampEventDomain.mapEventToDomain(event);
        var apiResponse = new ApiResponse<TimeStampEventDomain>();
        apiResponse.setData(domain);
        apiResponse.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/stop_fetch")
    public ResponseEntity<ApiResponse<TimeStampEventDomain>> handleStopFetch(@RequestBody EventTypeDto dto) {
        var event = eventService.createFromEventDto(dto);
        var domain = TimeStampEventDomain.mapEventToDomain(event);
        var apiResponse = new ApiResponse<TimeStampEventDomain>();
        apiResponse.setData(domain);
        apiResponse.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping("/fetch/timeout")
    public ResponseEntity<ApiResponse<TimeStampEventDomain>> handleFetchTimeout(@RequestBody EventTypeDto dto) {
        var apiResponse = new ApiResponse<TimeStampEventDomain>();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tamper")
    public ResponseEntity<ApiResponse<TimeStampEventDomain>> handleTamper(@RequestBody EventTypeDto dto) {
        var apiResponse = new ApiResponse<TimeStampEventDomain>();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/leakage")
    public ResponseEntity<ApiResponse<TimeStampEventDomain>> handleLeakage(@RequestBody EventTypeDto dto) {
        var apiResponse = new ApiResponse<TimeStampEventDomain>();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/battery_low")
    public ResponseEntity<ApiResponse<TimeStampEventDomain>> handleBatteryLow(@RequestBody EventTypeDto dto) {
        var apiResponse = new ApiResponse<TimeStampEventDomain>();
        return ResponseEntity.ok().build();
    }
}

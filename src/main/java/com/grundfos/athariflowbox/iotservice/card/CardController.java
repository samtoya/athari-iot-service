package com.grundfos.athariflowbox.iotservice.card;

import com.grundfos.athariflowbox.iotservice.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cards")
public record CardController(CardService cardService) {

    @GetMapping
    public ResponseEntity<ApiResponse<Collection<CardDomain>>> getAll() {
        List<CardDomain> domains = cardService.getAllCards().stream().map(CardDomain::mapEntityToDomain).collect(Collectors.toList());
        var apiResponse = new ApiResponse<Collection<CardDomain>>(Boolean.TRUE, null, domains);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CardDomain>> create(@RequestBody CardDto dto) {
        var card = cardService.createFromDto(dto);
        var domain = CardDomain.mapEntityToDomain(card);
        var apiResponse = new ApiResponse<>(Boolean.TRUE, null, domain);
        var uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{cardId}").buildAndExpand(card.getId()).toUri();

        return ResponseEntity.created(uri).body(apiResponse);
    }

    @GetMapping("/cardId")
    public ResponseEntity<ApiResponse<CardDomain>> getCard(@PathVariable("cardId") String cardId) {
        var apiResponse = new ApiResponse<CardDomain>();
        var optionalCard = cardService.findCardById(cardId);
        if (optionalCard.isEmpty()) {
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setMessage("Card not found!");
            apiResponse.setData(null);

            return ResponseEntity.notFound().build();
        }

        var domain = CardDomain.mapEntityToDomain(optionalCard.get());
        apiResponse.setData(domain);
        apiResponse.setSuccess(Boolean.TRUE);
        apiResponse.setMessage(null);

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<ApiResponse<Void>> deleteCard(@PathVariable("cardId") String cardId) {
        var apiResponse = new ApiResponse<Void>();

        try {
            cardService.deleteCardById(cardId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);

            return ResponseEntity.internalServerError()
                    .body(apiResponse);
        }
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<ApiResponse<CardDomain>> updateCard(@PathVariable("cardId") String cardId, @RequestBody CardDto dto) {
        var apiResponse = new ApiResponse<CardDomain>();
        Optional<Card> optionalCard = cardService.findCardById(cardId);
        if (optionalCard.isEmpty()) {
            apiResponse.setData(null);
            apiResponse.setMessage("Card not found with ID: " + cardId);
            return ResponseEntity.notFound().build();
        }
        var card = optionalCard.get();
        card.setHolder(dto.getHolder());
        card.setDisabledAt(dto.getDisabledAt());
        card.setReason(dto.getReason());
        card.setSerialNumber(dto.getSerialNumber());

        cardService.saveCard(card);
        var domain = CardDomain.mapEntityToDomain(card);
        apiResponse.setData(domain);
        apiResponse.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{cardId}/disable")
    public ResponseEntity<ApiResponse<CardDomain>> disableCard(@PathVariable("cardId") String cardId, @RequestBody CardDisableDto dto) {
        var apiResponse = new ApiResponse<CardDomain>();
        Optional<Card> optionalCard = cardService.findCardById(cardId);
        if (optionalCard.isEmpty()) {
            apiResponse.setData(null);
            apiResponse.setMessage("Card not found with ID: " + cardId);
            return ResponseEntity.notFound().build();
        }
        var card = optionalCard.get();
        card.setReason(dto.getReason());
        card.setDisabledAt(LocalDateTime.now());
        cardService.saveCard(card);
        var domain = CardDomain.mapEntityToDomain(card);
        apiResponse.setData(domain);
        apiResponse.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{cardId}/enable")
    public ResponseEntity<ApiResponse<CardDomain>> enableCard(@PathVariable("cardId") String cardId) {
        var apiResponse = new ApiResponse<CardDomain>();
        Optional<Card> optionalCard = cardService.findCardById(cardId);
        if (optionalCard.isEmpty()) {
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setData(null);
            apiResponse.setMessage("Card not found with ID: " + cardId);
            return ResponseEntity.notFound().build();
        }
        var card = optionalCard.get();
        card.setReason(null);
        card.setDisabledAt(null);
        cardService.saveCard(card);
        var domain = CardDomain.mapEntityToDomain(card);
        apiResponse.setData(domain);
        apiResponse.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{cardId}/isEnabled")
    public ResponseEntity<ApiResponse<Boolean>> isCardEnabled(@PathVariable("cardId") String cardId) {
        var apiResponse = new ApiResponse<Boolean>();
        Optional<Card> optionalCard = cardService.findCardById(cardId);
        if (optionalCard.isEmpty()) {
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setData(Boolean.FALSE);
            apiResponse.setMessage("Card not found with ID: " + cardId);
            return ResponseEntity.notFound().build();
        }
        var card = optionalCard.get();
        apiResponse.setSuccess(Boolean.TRUE);
        apiResponse.setData(card.getIsEnabled());

        return ResponseEntity.ok(apiResponse);
    }
}

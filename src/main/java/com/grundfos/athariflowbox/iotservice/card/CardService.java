package com.grundfos.athariflowbox.iotservice.card;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public record CardService(CardRepository cardRepository) {
    public Collection<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card createFromDto(CardDto dto) {
        var card = new Card();
        BeanUtils.copyProperties(dto, card);
        card.setId(UUID.randomUUID().toString());
        return card;
    }

    public Optional<Card> findCardById(String cardId) {
        return cardRepository.findById(cardId);
    }

    public void deleteCardById(String cardId) {
        cardRepository.deleteById(cardId);
    }

    public void saveCard(Card card) {
        cardRepository.saveAndFlush(card);
    }
}

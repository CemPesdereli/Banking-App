package com.eazybytes.cards.mapper;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;

public class CardsMapper {

public static Cards toCards(CardsDto cardsDto) {
    return Cards.builder()
            .mobileNumber(cardsDto.mobileNumber())
            .cardNumber(cardsDto.cardNumber())
            .cardType(cardsDto.cardType())
            .totalLimit(cardsDto.totalLimit())
            .amountUsed(cardsDto.amountUsed())
            .availableAmount(cardsDto.availableAmount())
            .build();
}



public static CardsDto toCardsDto(Cards cards) {
    return new CardsDto(
            cards.getMobileNumber(),
            cards.getCardNumber(),
            cards.getCardType(),
            cards.getTotalLimit(),
            cards.getAmountUsed(),
            cards.getAvailableAmount());
}

}

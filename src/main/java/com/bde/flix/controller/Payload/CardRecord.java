package com.bde.flix.controller.Payload;

import com.bde.flix.model.entity.userman.User;

import java.time.YearMonth;
import java.util.UUID;

//w zaleznosci od endpointu to moze byc id usera/karty
//do zweryfikowania XD
//TODO
public record CardRecord(UUID id,
                         long cardNumber,
                         int cvv,
                         YearMonth expireDate,
                         String cardHolder)
{
}


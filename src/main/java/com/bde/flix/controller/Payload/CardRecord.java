package com.bde.flix.controller.Payload;

import com.bde.flix.model.entity.userman.User;

import java.time.YearMonth;
import java.util.UUID;

public record CardRecord(UUID user,
                         long cardNumber,
                         int cvv,
                         YearMonth expireDate,
                         String cardHolder)
{
}

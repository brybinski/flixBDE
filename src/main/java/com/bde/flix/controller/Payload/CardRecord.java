package com.bde.flix.controller.Payload;

public record CardRecord(String user,
                         long cardNumber,
                         int cvv,
                         String expireDate,
                         String cardHolder)
{
}

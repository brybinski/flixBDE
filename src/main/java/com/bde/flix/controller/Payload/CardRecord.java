package com.bde.flix.controller.Payload;

import java.time.YearMonth;

public record CardRecord(String user,
                         long cardNumber,
                         int cvv,
                         YearMonth expireDate,
                         String cardHolder)
{
}

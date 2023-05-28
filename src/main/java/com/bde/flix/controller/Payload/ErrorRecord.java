package com.bde.flix.controller.Payload;


import java.sql.Timestamp;

public record ErrorRecord(Exception e, Timestamp time)
{
}

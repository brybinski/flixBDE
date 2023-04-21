package com.bde.flix.controller;


import java.sql.Timestamp;

public record ErrorRecord(Exception e, Timestamp time)
{
}

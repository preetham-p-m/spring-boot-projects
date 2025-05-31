package com.pmp.mono_tweet.exception.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Error {

    private LocalDateTime timeStamp;

    private String message;

    private String details;
}

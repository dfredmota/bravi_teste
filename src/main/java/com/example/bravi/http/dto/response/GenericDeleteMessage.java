package com.example.bravi.http.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericDeleteMessage {

    private int httpStatus;
    private String message;
}

package com.example.bravi.http.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PersonRequest {

    @NotBlank(message = "Informe o nome.")
    private String name;
}

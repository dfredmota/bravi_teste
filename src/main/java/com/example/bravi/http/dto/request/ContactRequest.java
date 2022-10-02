package com.example.bravi.http.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.util.UUID;

@Getter
@Setter
public class ContactRequest {

    @Length(max = 14, min = 11)
    private String telephone;

    @Email
    private String email;

    @Length(max = 14, min = 11)
    private String whatsapp;

    private UUID personId;
}

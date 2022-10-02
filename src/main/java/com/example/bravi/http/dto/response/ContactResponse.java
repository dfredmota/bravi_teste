package com.example.bravi.http.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {

    private UUID contactId;

    private String telephone;

    private String email;

    private String whatsapp;
}

package com.example.bravi.http.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

    private UUID personId;

    private String name;
}

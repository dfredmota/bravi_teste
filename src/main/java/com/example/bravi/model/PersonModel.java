package com.example.bravi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "Person", schema = "Teste")
public class PersonModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID personId;

    private String name;
}

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
@Table(name = "Contact", schema = "teste")
public class ContactModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID contactId;

    private String telephone;

    private String email;

    private String whatsapp;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonModel personModel;
}

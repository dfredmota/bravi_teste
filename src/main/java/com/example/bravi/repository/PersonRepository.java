package com.example.bravi.repository;

import com.example.bravi.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, UUID> {
}

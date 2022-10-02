package com.example.bravi.service;

import com.example.bravi.model.PersonModel;
import com.example.bravi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<PersonModel> findAll() {
        return personRepository.findAll();
    }

    public PersonModel findById(UUID uuid) {
        return personRepository.findById(uuid).orElseThrow(() -> new NoResultException("Pessoa não encontrada no sistema."));
    }

    public PersonModel save(PersonModel personModel) {
        return personRepository.save(personModel);
    }

    public PersonModel update(PersonModel personModel, UUID uuid) {
        personRepository.findById(uuid).orElseThrow(() -> new NoResultException("Pessoa não encontrada no sistema."));
        personModel.setPersonId(uuid);
        personRepository.save(personModel);
        return personModel;
    }

    public void delete(UUID uuid) {
        var person = personRepository.findById(uuid).orElseThrow(() -> new NoResultException("Pessoa não encontrada no sistema."));
        personRepository.delete(person);
    }
}

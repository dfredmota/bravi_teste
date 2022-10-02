package com.example.bravi.service;

import com.example.bravi.model.ContactModel;
import com.example.bravi.model.PersonModel;
import com.example.bravi.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public List<ContactModel> findAll(String telephone, String email, String whatsapp, UUID personId){

        return contactRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (telephone != null && !telephone.isEmpty()) {
                predicates.add(builder.like(builder.lower(root.get("telephone")),
                        "%" + telephone.toLowerCase() + "%"));
            }

            if (email != null && !email.isEmpty()) {
                predicates.add(builder.like(builder.lower(root.get("email")),
                        "%" + email.toLowerCase() + "%"));
            }

            if (whatsapp != null && !whatsapp.isEmpty()) {
                predicates.add(builder.like(builder.lower(root.get("whatsapp")),
                        "%" + whatsapp.toLowerCase() + "%"));
            }

            if(personId != null){
                Join<PersonModel,ContactModel> agentJoin = root.join("personModel");
                predicates.add(builder.equal(agentJoin.get("personId"), personId));
            }

            return builder.and(predicates.toArray(new Predicate[]{}));

        });
    }

    public ContactModel findById(UUID uuid){
        return contactRepository.findById(uuid).orElseThrow(() -> new NoResultException("Contato não encontrado no sistema."));
    }

    public ContactModel save(ContactModel contactModel){
        return contactRepository.save(contactModel);
    }

    public ContactModel update(ContactModel contactModel, UUID uuid){
        contactRepository.findById(uuid).orElseThrow(() -> new NoResultException("Contato não encontrado no sistema."));
        contactModel.setContactId(uuid);
        contactRepository.save(contactModel);
        return contactModel;
    }

    public void delete(UUID uuid){
        var contact = contactRepository.findById(uuid).orElseThrow(() -> new NoResultException("Contato não encontrado no sistema."));
        contactRepository.delete(contact);
    }
}

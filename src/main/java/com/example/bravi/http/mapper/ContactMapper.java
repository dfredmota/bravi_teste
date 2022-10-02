package com.example.bravi.http.mapper;

import com.example.bravi.http.dto.request.ContactRequest;
import com.example.bravi.http.dto.response.ContactResponse;
import com.example.bravi.model.ContactModel;
import com.example.bravi.model.PersonModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class ContactMapper {

    public static ContactModel toModel(ContactRequest contactRequest){
        return ContactModel.builder()
                .telephone(contactRequest.getTelephone())
                .email(contactRequest.getEmail())
                .whatsapp(contactRequest.getWhatsapp())
                .personModel(PersonModel.builder().personId(contactRequest.getPersonId()).build())
                .build();
    }

    public static ContactResponse toResponse(ContactModel contactModel){
        return ContactResponse.builder()
                .contactId(contactModel.getContactId())
                .telephone(contactModel.getTelephone())
                .email(contactModel.getEmail())
                .whatsapp(contactModel.getWhatsapp())
                .build();
    }

    public static List<ContactResponse> toContactResponseList(List<ContactModel> contactModelList){
        if (isNull(contactModelList) || contactModelList.isEmpty()){
            return new ArrayList<>();
        } else {
            return contactModelList.stream().map(ContactMapper::toResponse).collect(Collectors.toList());
        }
    }
}

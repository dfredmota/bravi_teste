package com.example.bravi.http.mapper;

import com.example.bravi.http.dto.request.PersonRequest;
import com.example.bravi.http.dto.response.PersonResponse;
import com.example.bravi.model.PersonModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class PersonMapper {

    public static PersonModel toModel(PersonRequest personRequest) {
        return PersonModel.builder()
                .name(personRequest.getName())
                .build();
    }

    public static PersonResponse toResponse(PersonModel personModel) {
        return PersonResponse.builder()
                .personId(personModel.getPersonId())
                .name(personModel.getName())
                .build();
    }

    public static List<PersonResponse> toPersonResponseList(List<PersonModel> personModelList) {
        if (personModelList.isEmpty() || isNull(personModelList)) {
            return new ArrayList<>();
        } else {
            return personModelList.stream().map(PersonMapper::toResponse).collect(Collectors.toList());
        }
    }
}

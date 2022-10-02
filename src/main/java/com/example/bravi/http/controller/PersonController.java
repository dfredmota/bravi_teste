package com.example.bravi.http.controller;

import com.example.bravi.http.controller.interfaces.IPersonController;
import com.example.bravi.http.dto.request.PersonRequest;
import com.example.bravi.http.dto.response.GenericDeleteMessage;
import com.example.bravi.http.dto.response.PersonResponse;
import com.example.bravi.http.mapper.PersonMapper;
import com.example.bravi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController implements IPersonController {

    private final PersonService personService;

    @GetMapping("/all")
    public ResponseEntity<List<PersonResponse>> findAll() {
        return ResponseEntity.ok().body(PersonMapper.toPersonResponseList(personService.findAll()));
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonResponse> findById(@PathVariable String personId) {
        return ResponseEntity.ok().body(PersonMapper.toResponse(personService.findById(UUID.fromString(personId))));
    }

    @PostMapping
    public ResponseEntity<PersonResponse> save(@RequestBody @Valid PersonRequest personRequest) {
        return ResponseEntity.ok().body(PersonMapper.toResponse(personService.save(PersonMapper.toModel(personRequest))));
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonResponse> update(@RequestBody @Valid PersonRequest personRequest, @PathVariable String personId) {
        return ResponseEntity.ok().body(PersonMapper.toResponse(personService.update(PersonMapper.toModel(personRequest), UUID.fromString(personId))));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<GenericDeleteMessage> delete(@PathVariable String personId) {
        GenericDeleteMessage response = new GenericDeleteMessage();
        response.setHttpStatus(200);
        response.setMessage("Pessoa deletada do sistema com sucesso!");
        return ResponseEntity.ok().body(response);
    }
}

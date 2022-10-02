package com.example.bravi.http.controller;

import com.example.bravi.http.controller.interfaces.IContactController;
import com.example.bravi.http.dto.request.ContactRequest;
import com.example.bravi.http.dto.response.ContactResponse;
import com.example.bravi.http.dto.response.GenericDeleteMessage;
import com.example.bravi.http.mapper.ContactMapper;
import com.example.bravi.service.ContactService;
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
@RequestMapping("/contact")
public class ContactController implements IContactController {

    private final ContactService contactService;

    @GetMapping("/all")
    public ResponseEntity<List<ContactResponse>> findAll(@RequestParam(name = "telephone", required = false) String telephone,
                                                         @RequestParam(name = "email", required = false) String email,
                                                         @RequestParam(name = "whatsapp", required = false) String whatsapp,
                                                         @RequestParam(name = "personId", required = false) UUID personId){

        var list = contactService.findAll(telephone, email, whatsapp, personId);
        return ResponseEntity.ok(ContactMapper.toContactResponseList(list));
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactResponse> findById(@PathVariable String contactId){
        return ResponseEntity.ok().body(ContactMapper.toResponse(contactService.findById(UUID.fromString(contactId))));
    }

    @PostMapping
    public ResponseEntity<ContactResponse> save(@RequestBody @Valid ContactRequest contactRequest){
        return ResponseEntity.ok().body(ContactMapper.toResponse(contactService.save(ContactMapper.toModel(contactRequest))));
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactResponse> update(@RequestBody @Valid ContactRequest contactRequest, @PathVariable String contactId){
        return ResponseEntity.ok().body(ContactMapper.toResponse(contactService.update(ContactMapper.toModel(contactRequest), UUID.fromString(contactId))));
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<GenericDeleteMessage> delete(@PathVariable String contactId){
        GenericDeleteMessage response = new GenericDeleteMessage();
        response.setHttpStatus(200);
        response.setMessage("Contato deletado do sistema com sucesso!");
        return ResponseEntity.ok().body(response);
    }
}

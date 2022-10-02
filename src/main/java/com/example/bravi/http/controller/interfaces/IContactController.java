package com.example.bravi.http.controller.interfaces;

import com.example.bravi.http.dto.request.ContactRequest;
import com.example.bravi.http.dto.response.ContactResponse;
import com.example.bravi.http.dto.response.GenericDeleteMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
@Tag(name = "Consulta os contatos.")
@SecurityRequirement(name = "bearerAuth")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/contact")
public interface IContactController {
    @Operation(summary = "Lista todos os contatos salvos no sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            [
                                              {
                                                "telephone": "DDD 90000-1111",
                                                "email": "Teste@gmail.com",
                                                "whatsapp": "DDD 90000-1111",
                                                "personId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
                                              }
                                            ]
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "/contact",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    ResponseEntity<List<ContactResponse>> findAll(@RequestParam(name = "telephone", required = false) String telephone,
                                                  @RequestParam(name = "email", required = false) String email,
                                                  @RequestParam(name = "whatsapp", required = false) String whatsapp,
                                                  @RequestParam(name = "personId", required = false) UUID personId);

    @Operation(summary = "Localiza um contato pelo Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "telephone": "DDD 90000-1111",
                                              "email": "Teste@gmail.com",
                                              "whatsapp": "DDD 90000-1111",
                                              "personId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "/{contactId}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/{contactId}")
    ResponseEntity<ContactResponse> findById(@PathVariable String contactId);

    @Operation(summary = "Salvar um novo contato no sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o cadastro seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "telephone": "DDD 90000-1111",
                                              "email": "Teste@gmail.com",
                                              "whatsapp": "DDD 90000-1111",
                                              "personId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "/contact",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @RequestBody(
            description = """
                    Dados para salvar um novo contato.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                      "telephone": "DDD 90000-1111",
                                      "email": "Teste@gmail.com",
                                      "whatsapp": "DDD 90000-1111",
                                      "personId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
                                    }
                                    """
                    )
            )
    )
    @PostMapping
    ResponseEntity<ContactResponse> save(@RequestBody @Valid ContactRequest contactRequest);

    @Operation(summary = "Atualiza o contato.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a atualização seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "telephone": "DDD 90000-1111",
                                              "email": "Teste@gmail.com",
                                              "whatsapp": "DDD 90000-1111",
                                              "personId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "/{contactId}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @PutMapping("/{contactId}")
    ResponseEntity<ContactResponse> update(@RequestBody @Valid ContactRequest contactRequest, @PathVariable String contactId);

    @Operation(summary = "Deleta um contato por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "Caso a deleção seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "httpStatus" : 201,
                                                "message": "Contato deletado do sistema com sucesso.",
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "/{contactId}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping("/{contactId]")
    ResponseEntity<GenericDeleteMessage> delete(@PathVariable String contactId);
}

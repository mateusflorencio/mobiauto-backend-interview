package com.mobiauto.revenda.main.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import com.mobiauto.revenda.application.contracts.HttpRequest;
import com.mobiauto.revenda.application.contracts.HttpResponse;
import com.mobiauto.revenda.application.contracts.Validations;
import com.mobiauto.revenda.application.controllers.revenda.CreateRevendaController;
import com.mobiauto.revenda.application.controllers.revenda.CreateRevendaRequest;
import com.mobiauto.revenda.application.validations.SampleValidation;
import com.mobiauto.revenda.data.repositories.revenda.CreateRevendaRepository;
import com.mobiauto.revenda.data.repositories.revenda.FindByCnpjRepository;
import com.mobiauto.revenda.domain.models.revenda.RevendaModel;
import com.mobiauto.revenda.domain.usecases.CreateRevendaUseCase;
import com.mobiauto.revenda.external.repositories.h2.revenda.RevendaEntity;
import com.mobiauto.revenda.external.repositories.h2.revenda.RevendaGateway;
import com.mobiauto.revenda.external.repositories.h2.revenda.RevendaMapper;
import com.mobiauto.revenda.external.repositories.h2.revenda.RevendaRepository;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;

@Configuration
@RestController
public class CreateRevenda {

  @Bean
  CreateRevendaUseCase createRevendaUseCase(CreateRevendaRepository createRevendaRepository,
      FindByCnpjRepository findByCnpjRepository) {
    return new CreateRevendaUseCase(createRevendaRepository, findByCnpjRepository);
  }

  @Bean
  RevendaGateway revendaGateway(RevendaRepository revendaRepository, RevendaMapper revendaMapper) {
    return new RevendaGateway(revendaRepository, revendaMapper);
  }

  @Bean
  RevendaMapper revendaMapper() {
    return new RevendaMapper();
  }

  @Bean
  // RevendaRepository
  RevendaEntity revendaEntity() {
    return new RevendaEntity();
  }

  @Bean
  Validations validations() {
    return new SampleValidation();
  }

  @Bean
  CreateRevendaController createRevendaController(CreateRevendaUseCase createRevendaUseCase, Validations validations) {
    return new CreateRevendaController(createRevendaUseCase, validations);
  }

  @Bean
  @PostMapping("/revenda")
  @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateRevendaRequest.class)))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Revenda criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RevendaModel.class))),
      @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
  })
  RouterFunction<ServerResponse> createRevendaRouter(CreateRevendaController createRevendaController) {
    return RouterFunctions.route(RequestPredicates.POST("/revenda"), request -> {
      @SuppressWarnings({ "rawtypes", "unchecked" })
      HttpRequest httpRequest = new HttpRequest(request.body(CreateRevendaRequest.class));
      @SuppressWarnings("unchecked")
      HttpResponse response = createRevendaController.handle(httpRequest);
      return ServerResponse.status(response.getStatus()).body(response.getBody());
    });
  }
}

package com.mobiauto.revenda.main.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import com.mobiauto.revenda.application.contracts.HttpRequest;
import com.mobiauto.revenda.application.contracts.HttpResponse;
import com.mobiauto.revenda.application.controllers.revenda.CreateRevendaController;
import com.mobiauto.revenda.application.controllers.revenda.CreateRevendaRequest;
import com.mobiauto.revenda.data.repositories.revenda.CreateRevendaRepository;
import com.mobiauto.revenda.data.repositories.revenda.FindByCnpjRepository;
import com.mobiauto.revenda.domain.usecases.CreateRevendaUseCase;
import com.mobiauto.revenda.external.repositories.h2.revenda.RevendaEntity;
import com.mobiauto.revenda.external.repositories.h2.revenda.RevendaGateway;
import com.mobiauto.revenda.external.repositories.h2.revenda.RevendaMapper;
import com.mobiauto.revenda.external.repositories.h2.revenda.RevendaRepository;

@Configuration
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
  CreateRevendaController createRevendaController(CreateRevendaUseCase createRevendaUseCase) {
    return new CreateRevendaController(createRevendaUseCase);
  }

  @Bean
  RouterFunction<ServerResponse> createRevendaRouter(CreateRevendaController createRevendaController) {
    return RouterFunctions.route(RequestPredicates.POST("/revenda"), request -> {
      HttpRequest httpRequest = new HttpRequest(request.body(CreateRevendaRequest.class));
      HttpResponse response = createRevendaController.handle(httpRequest);
      return ServerResponse.status(response.getStatus()).body(response.getBody());
    });
  }
}

package com.mobiauto.revenda.application.contracts;

public interface Controllers<Q>{
  HttpResponse handle(HttpRequest<Q> request);
}

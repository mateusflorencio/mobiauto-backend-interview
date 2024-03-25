package com.mobiauto.revenda.application.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HttpResponse {
  private Object body;
  private int status;

  public HttpResponse body(Object body) {
    this.body = body;
    return this;
  }

  public static HttpResponse ok() {
    HttpResponse httpResponse = new HttpResponse();
    httpResponse.status = 200;
    return httpResponse;
  }

  public static HttpResponse badRequest() {
    HttpResponse httpResponse = new HttpResponse();
    httpResponse.status = 400;
    return httpResponse;
  }

  public static HttpResponse notFound() {
    HttpResponse httpResponse = new HttpResponse();
    httpResponse.status = 404;
    return httpResponse;
  }

  public static HttpResponse internalServerError() {
    HttpResponse httpResponse = new HttpResponse();
    httpResponse.status = 500;
    return httpResponse;
  }

  public static HttpResponse unauthorized() {
    HttpResponse httpResponse = new HttpResponse();
    httpResponse.status = 401;
    return httpResponse;
  }

  public static HttpResponse forbidden() {
    HttpResponse httpResponse = new HttpResponse();
    httpResponse.status = 403;
    return httpResponse;
  }

  public static HttpResponse created() {
    HttpResponse httpResponse = new HttpResponse();
    httpResponse.status = 201;
    return httpResponse;
  }
}
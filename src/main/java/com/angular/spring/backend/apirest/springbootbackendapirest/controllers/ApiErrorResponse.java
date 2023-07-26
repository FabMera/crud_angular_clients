package com.angular.spring.backend.apirest.springbootbackendapirest.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {
    private int code;
    private String message;

}

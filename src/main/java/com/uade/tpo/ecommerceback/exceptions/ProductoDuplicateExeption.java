package com.uade.tpo.ecommerceback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "El producto ingresado ya existe!")
public class ProductoDuplicateExeption extends Exception{
}

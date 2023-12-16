package com.example.app.democustomannotation;

import com.example.app.democustomannotation.domain.Customer;
import com.example.app.democustomannotation.validator.ValidaEmail;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@SpringBootApplication
public class DemoCustomAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCustomAnnotationApplication.class, args);
	}

	@RestController
	@Validated
	static class ApiController {

		@GetMapping
		public List<String> example(@RequestBody @Valid List<@ValidaEmail(message = "email errado burro") String> customer) {
			return customer;
		}

	}

	@ControllerAdvice
	static class ApiExceptionHandler extends ResponseEntityExceptionHandler {

		@Override //tratamento de execeção para validação FIELD
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

			List<ObjectError> errors = ex.getBindingResult().getAllErrors();
			CustomError customError = null;
				for (ObjectError error : errors) {
					customError = new CustomError(error.getDefaultMessage());
			}

			return ResponseEntity.status(status.value()).body(customError);
		}

		@ExceptionHandler(ConstraintViolationException.class) // tratamento de exceção para TYPE_USE
		protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex ) {
			return ResponseEntity.ok(new CustomError(ex.getMessage()));
		}

		@Override
		protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
			return super.handleHandlerMethodValidationException(ex, headers, status, request);
		}



		record CustomError(String message) {}
	}
}

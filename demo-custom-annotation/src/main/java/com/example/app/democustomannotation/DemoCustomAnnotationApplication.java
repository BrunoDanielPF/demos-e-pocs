package com.example.app.democustomannotation;

import com.example.app.democustomannotation.validator.ValidaEmail;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@SpringBootApplication
public class DemoCustomAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCustomAnnotationApplication.class, args);
	}

	@RestController
	static class ApiController {

		@GetMapping
		public Customer example(@RequestBody @Valid Customer customer) {
			return customer;
		}

	}

	record Customer(@ValidaEmail String email){}


	@ControllerAdvice
	static class ApiExceptionHandler extends ResponseEntityExceptionHandler {

		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

			List<ObjectError> errors = ex.getBindingResult().getAllErrors();
			CustomError customError = null;
				for (ObjectError error : errors) {
					customError = new CustomError(error.getDefaultMessage());
			}

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customError);
		}

		record CustomError(String message) {}
	}
}

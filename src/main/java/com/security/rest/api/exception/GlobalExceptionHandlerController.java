package com.security.rest.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ProblemDetail> handleResourceConflictException(ResourceConflictException ex, HttpServletRequest request) {
        String fullURL = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
        String path = ServletUriComponentsBuilder.fromRequestUri(request).build().toString();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getReasonPhrase());
        problemDetail.setTitle(HttpStatus.CONFLICT.toString());
        problemDetail.setType(URI.create(path));
        problemDetail.setInstance(URI.create(fullURL));
        problemDetail.setProperties(Map.of("errorMessage", ex.getMessage()));
        return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        String fullURL = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
        String path = ServletUriComponentsBuilder.fromRequestUri(request).build().toString();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
        problemDetail.setTitle(HttpStatus.NOT_FOUND.toString());
        problemDetail.setType(URI.create(path));
        problemDetail.setInstance(URI.create(fullURL));
        problemDetail.setProperties(Map.of("errorMessage", ex.getMessage()));
        return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
        String fullURL = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
        String path = ServletUriComponentsBuilder.fromRequestUri(request).build().toString();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
        problemDetail.setTitle(HttpStatus.NOT_FOUND.toString());
        problemDetail.setType(URI.create(path));
        problemDetail.setInstance(URI.create(fullURL));
        problemDetail.setProperties(Map.of("errorMessage", ex.getMessage()));
        return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ProblemDetail> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        String fullURL = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
        String path = ServletUriComponentsBuilder.fromRequestUri(request).build().toString();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase());
        problemDetail.setTitle(HttpStatus.FORBIDDEN.name());
        problemDetail.setType(URI.create(path));
        problemDetail.setInstance(URI.create(fullURL));
        problemDetail.setProperties(Map.of("errorMessage", ex.getMessage()));
        return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ProblemDetail> handleBadCredentialsException(AuthorizationDeniedException ex, HttpServletRequest request) {
        String fullURL = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
        String path = ServletUriComponentsBuilder.fromRequestUri(request).build().toString();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
        problemDetail.setTitle(HttpStatus.UNAUTHORIZED.name());
        problemDetail.setType(URI.create(path));
        problemDetail.setInstance(URI.create(fullURL));
        problemDetail.setProperties(Map.of("errorMessage", ex.getMessage()));
        return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGlobalException(Exception ex, HttpServletRequest request) {
        String fullURL = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
        String path = ServletUriComponentsBuilder.fromRequestUri(request).build().toString();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        problemDetail.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.name());
        problemDetail.setType(URI.create(path));
        problemDetail.setInstance(URI.create(fullURL));
        problemDetail.setProperties(Map.of("errorMessage", ex.getMessage()));
        return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.ozkan.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductApi<D> {

    public ResponseEntity<?> productApiCreate(D d);

    public ResponseEntity<List<D>> productApiList();

    public ResponseEntity<?> productApiFindById(Long id);

    public ResponseEntity<?> productApiUpdateById(Long id,D d);

    public ResponseEntity<?> productApiDeleteById(Long id);



}

package com.ozkan.controller.Api;

import com.ozkan.business.dto.ProductDto;
import com.ozkan.business.service.IProductService;
import com.ozkan.controller.IProductApi;
import com.ozkan.utils.FrontEndPortUrl;
import error.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product/api")
@CrossOrigin(FrontEndPortUrl.REACT_FRONTEND_PORT_URL)
@RequiredArgsConstructor
public class ProductApi implements IProductApi<ProductDto> {

    private final IProductService productService;

    @Override
    @PostMapping("/add")
    public ResponseEntity<?> productApiCreate(@Valid @RequestBody ProductDto productDto) {
        ProductDto productCreateApi = (ProductDto) productService.productServiceCreate(productDto);

        // If dto is null, give a 404 error.
        if (productCreateApi == null) {
            ApiResult apiResultCreate = ApiResult.builder()
                    .status(404)
                    .error("Can not add task!")
                    .message("Can not find todo dto")
                    .path("localhost:4444/todo/api/create")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultCreate);
        }
        // If dto id is 0, give a bad request error
        else if (productCreateApi.getProductId() == 0) {
            ApiResult apiResultCreate = ApiResult.builder()
                    .status(400)
                    .error("Can not add task!")
                    .message("Todo Dto Bad Request")
                    .path("localhost:4444/todo/api/create")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(400).body(apiResultCreate);
        }
        // If dto id is not 0 and dto is not null, successfully add data
        return ResponseEntity.status(201).body(productService.productServiceCreate(productDto));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> productApiList() {

        return ResponseEntity.ok(productService.productServiceList());
    }

    @Override
    @GetMapping({"/find","/find/{id}"})
    public ResponseEntity<?> productApiFindById(@PathVariable(name = "id",required = false) Long id) {
        ProductDto productFindApi = (ProductDto) productService.productServiceFindById(id);

        if (productFindApi == null) {
            ApiResult apiResultFind = ApiResult.builder()
                    .status(404)
                    .error("Can not find task! ")
                    .message("Can not find ToDo Dto")
                    .path("localhost:4444/todo/api/find")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultFind);
        }

        return ResponseEntity.ok(productService.productServiceFindById(id));
    }

    @Override
    @PutMapping({"/update","/update/{id}"})
    public ResponseEntity<?> productApiUpdateById(@PathVariable(name = "id",required = false) Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok().body(productService.productServiceUpdateById(id,productDto));
    }

    @Override
    @DeleteMapping({"/delete","/delete/{id}"})
    public ResponseEntity<?> productApiDeleteById(@PathVariable(name = "id",required = false) Long id) {
        ProductDto apiToDelete = (ProductDto) productService.productServiceDeleteById(id);
        if(apiToDelete==null){
            ApiResult apiResultFind = ApiResult.builder()
                    .status(404)
                    .error("Can not find task! ")
                    .message("Can not find ToDo Dto")
                    .path("localhost:4444/todo/api/find")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultFind);
        }
        return ResponseEntity.ok(productService.productServiceDeleteById(id));
    }
}

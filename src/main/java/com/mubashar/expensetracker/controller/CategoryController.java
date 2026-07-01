package com.mubashar.expensetracker.controller;

import com.mubashar.expensetracker.dto.CategoryRequest;
import com.mubashar.expensetracker.dto.CategoryResponse;
import com.mubashar.expensetracker.entity.Category;
import com.mubashar.expensetracker.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest categoryRequest) {

        Category category = categoryService.saveCategory(categoryRequest);
        CategoryResponse categoryResponse = new CategoryResponse(category.getName(),category.getUserId());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(categoryResponse);

    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> searchByCategory() {
       List< CategoryResponse>response = categoryService.allCategories();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
/*
        @GetMapping("/category/{id}")
        public ResponseEntity<Category> categoryById (@PathVariable Long id){
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.searchCategoryById(id));

        }
        */

    @PutMapping("/updatecategory/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable  Long id, @RequestBody CategoryRequest categoryRequest){

      CategoryResponse category = categoryService.updateCategory(id,categoryRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory( @PathVariable Long id){

        categoryService.deleteCategory(id);

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();


    }



    }






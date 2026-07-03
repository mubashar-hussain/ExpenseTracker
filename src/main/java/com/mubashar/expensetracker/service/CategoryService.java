package com.mubashar.expensetracker.service;

import com.mubashar.expensetracker.dto.CategoryRequest;
import com.mubashar.expensetracker.dto.CategoryResponse;
import com.mubashar.expensetracker.entity.Category;
import com.mubashar.expensetracker.exceptions.CategoryNotFoundException;
import com.mubashar.expensetracker.repository.CategoryRepo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public Category saveCategory(CategoryRequest categoryReq) {

    Category category = new Category();
    category.setName(categoryReq.name());
    category.setUserId(categoryReq.userId());

        String categoryName = categoryReq.name().toUpperCase();

        if (categoryName.equals("FOOD") || categoryName.equals("HEALTH") || categoryName.equals("TRAVEL") || categoryName.equals("SHOPPING")) {

            return categoryRepo.save(category);

        } else {
            throw new CategoryNotFoundException(("Category Not Found") + categoryReq.name());
        }

    }

    public List<CategoryResponse> allCategories(Pageable pageable) {
        List<Category> allCategory = categoryRepo.findAll(pageable).getContent() ;

        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for(Category  category: allCategory){

            categoryResponses.add(new CategoryResponse(category.getName(),category.getUserId()));
        }
        return  categoryResponses;

    }

    public Category searchCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category Not Found" + id));
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest categoryReq) {

         categoryRepo.findById(id).orElseThrow(()-> new CategoryNotFoundException("Id not Exist"+ id));

        Category category1 = new Category();
        //category1.setId(categoryReq.Id());
        category1.setName(categoryReq.name());
        category1.setUserId(categoryReq.userId());

        return new CategoryResponse(category1.getName(), category1.getUserId());

    }

    public void deleteCategory(Long id) {

        if(categoryRepo.existsById(id)){

            categoryRepo.deleteById(id);
        }
        else{
            throw  new CategoryNotFoundException("Category not found");
        }
    }
}

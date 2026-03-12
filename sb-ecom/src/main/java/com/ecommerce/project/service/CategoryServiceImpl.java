package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

//    private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
        System.out.println(category + " has been added.");
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> foundCategoryOpt = categoryRepository.findById(categoryId);
        if(foundCategoryOpt.isPresent())
            categoryRepository.delete(foundCategoryOpt.get());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such element !");
        return "Category with category id: " + foundCategoryOpt.get() + "has been removed from DB.";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Optional<Category> foundCategoryOpt = categoryRepository.findById(categoryId);

        if(foundCategoryOpt.isPresent()){
            Category existingCategory = foundCategoryOpt.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category savedCategory = categoryRepository.save(existingCategory);
            return savedCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!");
        }
    }
}

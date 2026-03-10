package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        categories.forEach(category -> System.out.println("category " + category));
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
        System.out.println(category + " has been added.");
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                        .filter(c -> c.getCategoryId() == (categoryId))
                        .findFirst()
                        .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!"));
        categories.remove(category);
        System.out.println("Deleted category = " + category);
        return "Category with category id: " + category + "has been removed from DB.";
    }

    @Override
    public Category updateCategory(Category category, long categoryId) {
        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst();
        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!");
        }
    }
}

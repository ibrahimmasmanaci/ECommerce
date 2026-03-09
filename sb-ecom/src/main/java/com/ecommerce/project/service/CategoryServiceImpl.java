package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                .findFirst().orElse(null);
        if (category == null)
            return "Category not found!";
        categories.remove(category);
        System.out.println("Deleted category = " + category);
        return "Category with category id: " + category + "has been removed from DB.";
    }


}

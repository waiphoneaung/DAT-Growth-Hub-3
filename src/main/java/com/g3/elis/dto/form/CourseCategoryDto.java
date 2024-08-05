package com.g3.elis.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseCategoryDto {
	


    @NotBlank(message = "Category name is required")
    @Size(max = 255, message = "Category name must be less than 50 characters")
    private String categoryName;

   

    public CourseCategoryDto() {
    }

    public CourseCategoryDto(String categoryName) {
        
        this.categoryName = categoryName;
       
    }

    

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

   

}

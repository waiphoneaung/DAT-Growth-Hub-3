package com.g3.elis.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseTagDto {
	
	

    @NotBlank(message = "Tag name is required")
    @Size(max = 50, message = "Tag name must be less than 50 characters")
    private String tagName;

    

    public CourseTagDto() {
    }

    public CourseTagDto(String tagName) {
        
        this.tagName = tagName;
        
    }

    

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    

}

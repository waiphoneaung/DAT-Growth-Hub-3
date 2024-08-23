package com.g3.elis.serviceImpl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.CourseCategoryDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.CourseCategory;
import com.g3.elis.repository.CourseCategoryRepository;
import com.g3.elis.service.CourseCategoryService;

import jakarta.transaction.Transactional;

@Service
public class CourseCategoryServiceImpl implements CourseCategoryService{
	@Autowired
    private CourseCategoryRepository courseCategoryRepository;

   @Override
	public void saveCourseCategory(CourseCategory courseCategory) {
		courseCategoryRepository.save(courseCategory);		
		
	}

   @Override
   public List<CourseCategory> getAllCourseCategories() {
       return courseCategoryRepository.findAll();
   }
   
   @Override
   public CourseCategory getCourseCategoryById(int id) {
       return courseCategoryRepository.findById(id).orElse(null);
               
   }

   
   @Transactional
   @Override
   public void updateCourseCategory(int id, CourseCategoryDto courseCategoryDto) {
       CourseCategory courseCategory = getCourseCategoryById(id);
       courseCategory.setCategoryName(courseCategoryDto.getCategoryName());
       
       courseCategoryRepository.save(courseCategory);
   }

   @Transactional
   @Override
   public void deleteCourseCategoryById(Long id) {
       courseCategoryRepository.deleteById(id);
   }

   
   public List<CourseCategory> searchCourseCategoriesByName(String categoryName) {
       return courseCategoryRepository.findByCategoryNameContainingIgnoreCase(categoryName);
   }

@Override
public List<CourseCategory> getCourseCategoryByUsername(String name) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<CourseCategory> getCourseCategoryByUserId(int id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Page<CourseCategory> getPaginatedCourseCategories(int page, int size) {
	Pageable pageable = PageRequest.of(page, size);
    return courseCategoryRepository.findAll(pageable);
}

@Override
public Page<CourseCategory> searchPaginatedCourseCategoriesByName(String search, int page, int pageSize) {
	Pageable pageable = PageRequest.of(page, pageSize);
    return courseCategoryRepository.findByCategoryNameContainingIgnoreCase(search, pageable);
}

@Override
public List<CourseCategory> findTop3MostEnrolledCategories() {
	
	    return courseCategoryRepository.findTop3MostEnrolledCategories();
	
}



}

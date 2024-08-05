package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g3.elis.model.Question;

public interface QuestionReopsitory extends JpaRepository<Question, Integer>{

}

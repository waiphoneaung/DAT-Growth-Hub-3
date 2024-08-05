package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g3.elis.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}

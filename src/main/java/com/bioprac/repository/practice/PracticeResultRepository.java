package com.bioprac.repository.practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioprac.model.practice.PracticeResult;

@Repository
public interface PracticeResultRepository extends JpaRepository<PracticeResult, Integer> {

	public PracticeResult findByUserName(String userName);

}

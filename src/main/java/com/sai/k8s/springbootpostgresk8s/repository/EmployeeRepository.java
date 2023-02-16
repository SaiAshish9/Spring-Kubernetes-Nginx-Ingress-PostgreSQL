package com.sai.k8s.springbootpostgresk8s.repository;

import com.sai.k8s.springbootpostgresk8s.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}

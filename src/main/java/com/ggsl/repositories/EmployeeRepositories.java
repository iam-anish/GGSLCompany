package com.ggsl.repositories;

import com.ggsl.entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

public interface EmployeeRepositories extends MongoRepository<Employee, String> {

}

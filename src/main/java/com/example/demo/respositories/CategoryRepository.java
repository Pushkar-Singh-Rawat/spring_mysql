package com.example.demo.respositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Category;
import java.lang.String;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>{

 Optional<Category> findByDescription(String description);
 
}

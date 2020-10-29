package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer> {
}

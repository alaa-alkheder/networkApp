package com.javainuse.dao;

import com.javainuse.model.DAOLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<DAOLog,Integer> {
}

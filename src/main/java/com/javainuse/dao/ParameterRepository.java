package com.javainuse.dao;

import com.javainuse.model.DAOParameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
*/


@Repository
public interface ParameterRepository extends CrudRepository<DAOParameter,Integer>  {
}

package com.javainuse.dao;

import com.javainuse.model.DAOEstate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EstatesRepository extends CrudRepository<DAOEstate, Integer> {
    List<DAOEstate> findByStateIsFalse();

    @Override
    Optional<DAOEstate> findById(Integer var1);
}

package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Parent;
import org.springframework.data.repository.CrudRepository;

public interface ParentRepository extends CrudRepository<Parent, String> {

        Parent findByPPHONE(String pPHONE);
}

package com.pkovar.dbviewer.repository;

import com.pkovar.dbviewer.entity.ConnectionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends CrudRepository<ConnectionEntity, Long> {
}

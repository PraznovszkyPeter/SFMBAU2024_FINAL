package hu.unideb.inf.sfm.bau_backend.repository;

import hu.unideb.inf.sfm.bau_backend.entity.FormEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormRepository extends JpaRepository<FormEntity, Integer> { }

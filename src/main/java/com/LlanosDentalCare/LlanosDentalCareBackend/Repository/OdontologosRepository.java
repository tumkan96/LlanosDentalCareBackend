package com.LlanosDentalCare.LlanosDentalCareBackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.LlanosDentalCare.LlanosDentalCareBackend.Model.OdontologosModel;


public interface OdontologosRepository extends JpaRepository<OdontologosModel, Long>{
	@Query(value="SELECT *FROM personas ORDER BY id_persona ASC", nativeQuery = true)
	List<OdontologosModel>findAllOrderedByIdOdontologo();
}

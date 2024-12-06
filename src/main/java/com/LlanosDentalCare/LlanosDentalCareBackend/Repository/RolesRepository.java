package com.LlanosDentalCare.LlanosDentalCareBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.LlanosDentalCare.LlanosDentalCareBackend.Model.RolesModel;

public interface RolesRepository extends JpaRepository<RolesModel, Integer> {
}

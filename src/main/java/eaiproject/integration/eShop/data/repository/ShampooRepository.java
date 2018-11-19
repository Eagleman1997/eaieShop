package eaiproject.integration.eShop.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eaiproject.eaiprojectOrderPlacement.data.domain.Shampoo;


@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Integer>{

	public List<Shampoo> findShampoosByShampooId(@Param("shampoo_id") Integer ShampooId);
}

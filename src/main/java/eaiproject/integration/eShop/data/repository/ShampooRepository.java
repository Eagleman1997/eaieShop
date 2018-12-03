package eaiproject.integration.eShop.data.repository;

import eaiproject.integration.eShop.data.domain.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Integer>{}

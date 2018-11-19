package eaiproject.integration.eShop.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import eaiproject.eaiprojectOrderPlacement.data.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	public List<Order> findOrdersByOrderId(@Param("order_id") Integer OrderId);
}

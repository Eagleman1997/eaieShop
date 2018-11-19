package eaiproject.integration.eShop.business.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import eaiproject.eaiprojectInventory.data.domain.Customer;
import eaiproject.eaiprojectInventory.data.domain.Inventory;
import eaiproject.eaiprojectInventory.data.domain.Order;
import eaiproject.eaiprojectInventory.data.domain.PackingSlip;
import eaiproject.eaiprojectInventory.data.domain.Shampoo;
import eaiproject.eaiprojectInventory.data.repository.CustomerRepository;
import eaiproject.eaiprojectInventory.data.repository.InventoryRepository;
import eaiproject.eaiprojectInventory.data.repository.OrderRepository;
import eaiproject.eaiprojectInventory.data.repository.PackingSlipRepository;
import eaiproject.eaiprojectInventory.data.repository.ShampooRepository;

public class eShopService {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	private ShampooRepository shampooRespository;
	private OrderRepository orderRepository;
	private PackingSlipRepository packingSlipRepository;
	private CustomerRepository customerRepository;
	
	private Logger logger = LoggerFactory.getLogger(eShopService.class);
	
    public PackingSlip fetchGoods(Long customerId, String reference, List<Order> items) throws Exception {
        logger.info("fetchGoods() with customerId " + customerId + " and reference " + reference + " called and going to pick "+ items.size() +" items in the inventory");
        for(long seconds = items.size(); seconds > 0; seconds--) {
            logger.info(seconds + " items remaining");
            Thread.sleep(1000);
        }
        // ...
        PackingSlip packingSlip = new PackingSlip();
        logger.info("Packing slip generated with packing slip id: " + packingSlip.getPacking_slip_id());
        return packingSlip;
    }
	
	/**
	 * Invenvory
	 */
	public Inventory createInventory(Integer inventory_id, Integer shampoo_id, Integer amount_of_stock, String location) {
		Inventory inventory = new Inventory(inventory_id, shampoo_id, amount_of_stock, location);
		return inventoryRepository.save(inventory);
	}
	
	public Inventory readInventoryById(String inventoryId) {
		return inventoryRepository.findById(Integer.parseInt(inventoryId)).orElse(null);
	}
	
	public Inventory updateInventory(String inventory_id, Integer shampoo_id, Integer amount_of_stock, String location) {
		Inventory inventory = new Inventory(Integer.parseInt(inventory_id), shampoo_id, amount_of_stock, location);
		inventory.setInventory_id(Integer.parseInt(inventory_id));
		return inventoryRepository.save(inventory);
	}
	
	/**
	 * Shampoo
	 */
	public Shampoo createShampoo(Integer shampoo_id, String name, String brand, String type, Double price) {
		Shampoo shampoo = new Shampoo(shampoo_id, name, brand, type, price);
		return shampooRespository.save(shampoo);
	}
	
	public List<Shampoo> readShampooById(String shampooId) {
		return shampooRespository.findShampoosByShampooId(Integer.parseInt(shampooId));
	}
	
	public Shampoo updateShampoo(String shampoo_id, String name, String brand, String type, Double price) {
		Shampoo shampoo = new Shampoo(Integer.parseInt(shampoo_id), name , brand, type, price);
		shampoo.setShampoo_id(Integer.parseInt(shampoo_id));
		return shampooRespository.save(shampoo);
	}
	
	/**
	 * Order
	 */
	public Order createOrder(Integer order_id, Double total_order_price) {
		Order order = new Order(order_id, total_order_price);
		return orderRepository.save(order);
	}
	
	public List<Order> readOrderById(String orderId) {
		return orderRepository.findOrderByOrderId(Integer.parseInt(orderId));
	}
	
	public Order updateOrder(String order_id, Double total_order_price) {
		Order order = new Order(Integer.parseInt(order_id), total_order_price);
		order.setOrder_id(Integer.parseInt(order_id));
		return orderRepository.save(order);
	}
	
	/**
	 * PackingSlip
	 */
	public PackingSlip createPackingSlip(Integer packing_slip_id) {
		PackingSlip packingSlip = new PackingSlip(packing_slip_id);
		return packingSlipRepository.save(packingSlip);
	}
	
	public PackingSlip readPackingSlipById(String packingSlipId) {
		return packingSlipRepository.findPackingSlipByPackingSlipId(Integer.parseInt(packingSlipId));
	}
	
	public PackingSlip updatePackingSlip(String packing_slip_id) {
		PackingSlip packingSlip = new PackingSlip(Integer.parseInt(packing_slip_id));
		packingSlip.setPacking_slip_id(Integer.parseInt(packing_slip_id));
		return packingSlipRepository.save(packingSlip);
	}		

}

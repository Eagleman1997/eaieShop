package eaiproject.integration.eShop.data.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Order {
	
	@Id @GeneratedValue
	private Integer order_id;
	private Double total_order_price;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation_date;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_fk")
	private List<Shampoo> shampoos;
	
	public Order() {
		super();
	}

	public Order(Integer order_id, Double total_order_price, Date creation_date) {
		this.order_id = order_id;
		this.total_order_price = total_order_price;
		this.creation_date = creation_date;
		
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Double getTotal_order_price() {
		return total_order_price;
	}

	public void setTotal_order_price(Double total_order_price) {
		this.total_order_price = total_order_price;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public List<Shampoo> getShampoos() {
		return shampoos;
	}

	public void setShampoos(List<Shampoo> shampoos) {
		this.shampoos = shampoos;
	}

}

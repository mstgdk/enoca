package com.enoca.repository;

import com.enoca.DTO.request.DateRequest;
import com.enoca.domain.Customer;
import com.enoca.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "select * from t_order where t_order.create_date>=:givenDate", nativeQuery = true)
    List<Order> findAllByDate(@Param("givenDate") LocalDateTime givenDate);


    //List<Order> findAll(Customer customer);//****
    @Query(value = "select * from t_order where t_order.customer_id=:id", nativeQuery = true)
    List<Order> findAllOrders(@Param("id") Long id);

    @Query(value = "select * from t_order where t_order.customer_id=:id", nativeQuery = true)
    List<Order> findAllOrdersByCustomerId(Long id);

}

package edu.miu.junitexam;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Custom query to find orders by the user
    List<Order> findByUser(User user);
}

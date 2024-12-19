package edu.miu.junitexam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Method to get orders by user ID
    public List<Order> getOrdersByUser(Long userId) {
        User user = new User();
        user.setId(userId);  // Creating a user object with only the ID to pass into the query

        return orderRepository.findByUser(user);  // Fetch orders by user
    }
}


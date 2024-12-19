package edu.miu.junitexam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRewardService {

    @Autowired
    private OrderService orderService;

    public int calculateRewardPoints(User user) {
        int rewardPoints = 0;
        List<Order> orders = orderService.getOrdersByUser(user.getId());

        for (Order order : orders) {
            if (order.getTotalPrice() > 100) {
                rewardPoints += 10; // Reward 10 points for orders above $100
            } else if (order.getTotalPrice() > 50) {
                rewardPoints += 5; // Reward 5 points for orders above $50
            }

            if (user.isVip()) {
                rewardPoints += 2; // Bonus points for VIP users
            }
        }

        return rewardPoints;
    }

    public boolean deductRewardPoints(User user, int pointsToDeduct) {
        if (user.getRewardPoints() < pointsToDeduct) {
            return false; // Insufficient points
        }

        user.setRewardPoints(user.getRewardPoints() - pointsToDeduct);
        return true;
    }
}


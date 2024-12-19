package edu.miu.junitexam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.Test;

public class UserRewardServiceTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private UserRewardService userRewardService;

    private User regularUser;
    private User vipUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Create mock users
        regularUser = new User();
        regularUser.setId(1L);
        regularUser.setName("Regular User");
        regularUser.setVip(false);
        regularUser.setRewardPoints(50);

        vipUser = new User();
        vipUser.setId(2L);
        vipUser.setName("VIP User");
        vipUser.setVip(true);
        vipUser.setRewardPoints(100);
    }

    @Test
    public void testCalculateRewardPoints_forRegularUser() {
        // Mock orders for regular user
        List<Order> orders = Arrays.asList(
                new Order(1L, regularUser, 60),  // 5 points (order > $50)
                new Order(2L, regularUser, 120)  // 10 points (order > $100)
        );

        when(orderService.getOrdersByUser(regularUser.getId())).thenReturn(orders);

        int rewardPoints = userRewardService.calculateRewardPoints(regularUser);

        assertEquals(15, rewardPoints);  // 5 + 10 points
    }

    @Test
    public void testCalculateReward

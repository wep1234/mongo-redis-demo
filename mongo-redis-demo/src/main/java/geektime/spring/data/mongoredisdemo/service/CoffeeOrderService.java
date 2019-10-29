package geektime.spring.data.mongoredisdemo.service;

import geektime.spring.data.mongoredisdemo.model.Coffee;
import geektime.spring.data.mongoredisdemo.model.CoffeeOrder;
import geektime.spring.data.mongoredisdemo.model.OrderState;
import geektime.spring.data.mongoredisdemo.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author: wep
 * @since: 2019/10/29
 */
@Slf4j
@Service
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrder createOrder(String customer, Coffee...coffee){
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffee))
                .orderState(OrderState.INIT)
                .createTime(new Date())
                .updateTime(new Date()).build();
        CoffeeOrder saved = coffeeOrderRepository.save(order);
        log.info("New Order: {}", saved);
        return order;
    }
}

package geektime.spring.data.mongoredisdemo.repository;

import geektime.spring.data.mongoredisdemo.model.CoffeeOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: wep
 * @since: 2019/10/29
 */
public interface CoffeeOrderRepository extends MongoRepository<CoffeeOrder,String> {
}

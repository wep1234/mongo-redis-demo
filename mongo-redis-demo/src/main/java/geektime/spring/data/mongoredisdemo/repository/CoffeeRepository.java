package geektime.spring.data.mongoredisdemo.repository;

import geektime.spring.data.mongoredisdemo.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author: wep
 * @since: 2019/10/29
 */
public interface CoffeeRepository extends MongoRepository<Coffee,String> {
    List<Coffee> findByName(String name);
}

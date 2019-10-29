package geektime.spring.data.mongoredisdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @Author: wep
 * @since: 2019/10/29
 */
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeOrder {
    @Id
    private String id;
    private String customer;
    private List<Coffee> items;
    private OrderState orderState;
    private Date createTime;
    private Date updateTime;
}

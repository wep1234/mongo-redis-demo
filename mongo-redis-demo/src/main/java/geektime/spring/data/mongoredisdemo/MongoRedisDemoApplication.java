package geektime.spring.data.mongoredisdemo;

import geektime.spring.data.mongoredisdemo.converter.BytesToMoneyConverter;
import geektime.spring.data.mongoredisdemo.converter.MoneyReadConverter;
import geektime.spring.data.mongoredisdemo.converter.MoneyToBytesConverter;
import geektime.spring.data.mongoredisdemo.model.Coffee;
import geektime.spring.data.mongoredisdemo.repository.CoffeeOrderRepository;
import geektime.spring.data.mongoredisdemo.repository.CoffeeRepository;
import geektime.spring.data.mongoredisdemo.service.CoffeeOrderService;
import geektime.spring.data.mongoredisdemo.service.CoffeeService;
import io.lettuce.core.ReadFrom;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableMongoRepositories
@EnableRedisRepositories
public class MongoRedisDemoApplication implements CommandLineRunner {
	@Autowired
	private CoffeeRepository coffeeRepository;
	@Autowired
	private CoffeeOrderRepository coffeeOrderRepository;
	@Autowired
	private CoffeeService coffeeService;
	@Autowired
	private CoffeeOrderService coffeeOrderService;

	public static void main(String[] args) {
		SpringApplication.run(MongoRedisDemoApplication.class, args);
	}

	@Bean
	public LettuceClientConfigurationBuilderCustomizer customizer() {
		return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
	}

	@Bean
	public RedisCustomConversions redisCustomConversions() {
		return new RedisCustomConversions(
				Arrays.asList(new MoneyToBytesConverter(), new BytesToMoneyConverter()));
	}

	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
	}

	@Override
	public void run(String... args) throws Exception {
		deleteAll();
//		initDatas();
//		Optional<Coffee> c = coffeeService.findSimpleCoffeeFromCache("mocha");
//		log.info("Coffee {}", c);
//		Optional<Coffee> l = coffeeService.findSimpleCoffeeFromCache("latte");
//		log.info("Coffee {}", l);
//		coffeeOrderService.createOrder("hanyan",c.get(),l.get());
//		for (int i = 0; i < 5; i++) {
//			c = coffeeService.findSimpleCoffeeFromCache("mocha");
//			l = coffeeService.findSimpleCoffeeFromCache("latte");
//		}
//		log.info("Value from Redis: {}", c);
//		log.info("Value from Redis: {}", l);
	}

	private void deleteAll() {
		coffeeRepository.deleteAll();
		coffeeOrderRepository.deleteAll();
	}

	private void initDatas() {
		Coffee mocha = Coffee.builder()
				.name("mocha")
				.price(Money.of(CurrencyUnit.of("CNY"), 25.0))
				.createTime(new Date())
				.updateTime(new Date()).build();
		Coffee espresso = Coffee.builder()
				.name("espresso")
				.price(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.createTime(new Date())
				.updateTime(new Date()).build();
		Coffee latte = Coffee.builder()
				.name("latte")
				.price(Money.of(CurrencyUnit.of("CNY"), 30.0))
				.createTime(new Date())
				.updateTime(new Date()).build();
		coffeeRepository.saveAll(Arrays.asList(mocha,espresso,latte));
	}
}

package geektime.spring.data.mongoredisdemo.converter;

import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.springframework.core.convert.converter.Converter;
import org.bson.json.StrictJsonWriter;
import org.joda.money.Money;
import org.springframework.stereotype.Component;

/**
 * @Author: wep
 * @since: 2019/10/29
 */
@Component
public class MoneyReadConverter implements Converter<Document, Money> {


    @Override
    public Money convert(Document source) {
        Document money = (Document)source.get("money");
        double amount = Double.parseDouble(money.getString("amount"));
        String currency = ((Document)money.get("currency")).getString("code");
        return Money.of(CurrencyUnit.of(currency),amount);
    }
}

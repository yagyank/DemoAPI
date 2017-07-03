
import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.coinbase.api.Coinbase;
import com.coinbase.api.CoinbaseBuilder;
import com.coinbase.api.entity.Quote;
import com.coinbase.api.entity.Response;
import com.coinbase.api.exception.CoinbaseException;
import com.coinbase.api.exception.UnspecifiedAccount;

public class API {

	public static void main(String args[]) throws UnspecifiedAccount, IOException, CoinbaseException {
		Coinbase cb = new CoinbaseBuilder()
                .withApiKey("TdJP32LPFcXdQbbD", "1kzhSNVFoMsLD17h7BFTW5sUKxg97lja")
                .build();
		System.out.println(cb.getSpotPrice(CurrencyUnit.SGD));
		Quote q = cb.getBuyQuote(Money.parse("BTC 1"));
		Map<String, Money> fees = q.getFees();
		System.out.println(q.getSubtotal());
		System.out.println(q.getTotal());
	//	  .get();
	}
}

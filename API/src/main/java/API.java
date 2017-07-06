
import java.awt.PageAttributes.MediaType;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Map;

import javax.swing.text.Document;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.coinbase.api.Coinbase;
import com.coinbase.api.CoinbaseBuilder;
import com.coinbase.api.entity.Quote;
import com.coinbase.api.entity.Response;
import com.coinbase.api.exception.CoinbaseException;
import com.coinbase.api.exception.UnspecifiedAccount;

public class API {

	private static String getBuyCoinBase() throws IOException, CoinbaseException {
//		Coinbase cb = new CoinbaseBuilder()
//                .withApiKey("TdJP32LPFcXdQbbD", "1kzhSNVFoMsLD17h7BFTW5sUKxg97lja")
//                .build();
//		System.out.println(cb.getSpotPrice(CurrencyUnit.USD));
//		Quote q = cb.getBuyQuote(Money.parse("BTC 1"));
//		Map<String, Money> fees = q.getFees();
//		System.out.println(q.getFees());
//		System.out.println(q.getSubtotal());
//		return q.getTotal().toString();
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("https://www.coinbase.com/api/v2/prices/BTC-SGD/spot?");
		HttpResponse response = client.execute(request);
		// Get the response
		ResponseHandler<String> rHandler = new BasicResponseHandler();
		String line = rHandler.handleResponse(response);
		return line;
		//return line;
	}
	
	public static String getBuyZebpay() throws ClientProtocolException, IOException {
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("https://api.zebpay.com/api/v1/ticker?currencyCode=INR");
		HttpResponse response = client.execute(request);
		// Get the response
		ResponseHandler<String> rHandler = new BasicResponseHandler();
		String line = rHandler.handleResponse(response);
		return line;
	}
	
	public static String getBuyCoinhako() throws ClientProtocolException, IOException {
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("https://www.coinhako.com/api/v1/price/currency/BTCSGD");
		HttpResponse response = client.execute(request);
		// Get the response
		ResponseHandler<String> rHandler = new BasicResponseHandler();
		String line = rHandler.handleResponse(response);
		return line;
	}
	
	public static String getBuyFybsg() throws IOException {
		
		org.jsoup.nodes.Document doc = Jsoup.connect("http://www.fybsg.com").get();

		//Element link = ((Element) doc).select("a").first();
		Elements relHref = doc.getElementsByClass("price");
		return relHref.text();
	}
	public static void main(String args[]) throws UnspecifiedAccount, IOException, CoinbaseException {
		
	String coinBaseBuy = getBuyCoinBase();
	System.out.println(coinBaseBuy);
	String zebpayBuy = getBuyZebpay();
	System.out.println(zebpayBuy);
	String coinhakoBuy = getBuyCoinhako();
	System.out.println(coinhakoBuy);
	String fybsgBuy = getBuyFybsg();
	System.out.println(fybsgBuy);
	}
}

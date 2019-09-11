package core;

import java.io.*;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class CurrencyRate {

	static String CurrencRate(String pair_code) throws Exception {
		String url_currconv = "https://free.currconv.com/api/v7/convert";
		String api_key = "";  // !!! INPUT YOUR KEY
		JSONObject json = new JSONObject();
		JSONParser jp = new JSONParser();
		URL rate_url = new URL(url_currconv + "?q=" + pair_code + "&compact=ultra&apiKey=" + api_key);
		json = (JSONObject) jp
				.parse(new BufferedReader(new InputStreamReader(rate_url.openConnection().getInputStream())));
		return json.get(pair_code).toString();
	}

	public static void main(String[] args) throws Exception {

		System.out.println("USD_EUR: " + CurrencRate("USD_EUR"));
		System.out.println("USD_CNY: " + CurrencRate("USD_CNY"));
		System.out.println("USD_GBP: " + CurrencRate("USD_GBP"));
		System.out.println("USD_UAH: " + CurrencRate("USD_UAH"));
		System.out.println("USD_RUB: " + CurrencRate("USD_RUB"));
	}
}

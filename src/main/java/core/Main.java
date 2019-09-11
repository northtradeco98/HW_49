package core;


import java.math.*;
import java.util.logging.*;
import java.util.regex.*;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;

public class Main {

	public static void main(String[] args) throws Exception {

		String us_currency_symbol = "$";
		String url = "https://www.gnc.com/gnc/";

		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		WebDriver driver;
		driver = new SafariDriver();
		driver.manage().window().maximize();

		driver.get(url);

		Thread.sleep(5000);
		String title = driver.getTitle();
		String price = driver.findElement(By.xpath("//*[@id=\"c1d8d8a6c5abda5d1f93215acd\"]/div[5]/span[1]")).getText().trim();
		
		String regex = "^([\\$]?[1-9]{2}[\\.]?[9-9]{2})$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(price);
		m.find();
		
		double original_price = Double.parseDouble(m.group(1).replace("$", ""));
		

		String rate1 = CurrencyRate.CurrencRate("USD_EUR");
		double rate01 = Double.parseDouble(rate1);
		String rate2 = CurrencyRate.CurrencRate("USD_CNY");
		double rate02 = Double.parseDouble(rate2);
		String rate3 = CurrencyRate.CurrencRate("USD_GBP");
		double rate03 = Double.parseDouble(rate3);
		String rate4 = CurrencyRate.CurrencRate("USD_UAH");
		double rate04 = Double.parseDouble(rate4);
		String rate5 = CurrencyRate.CurrencRate("USD_RUB");
		double rate05 = Double.parseDouble(rate5);

		double eur_price = new BigDecimal(original_price * rate01).setScale(2, RoundingMode.HALF_UP).doubleValue();
		double cny_price = new BigDecimal(original_price * rate02).setScale(2, RoundingMode.HALF_UP).doubleValue();
		double gbp_price = new BigDecimal(original_price * rate03).setScale(2, RoundingMode.HALF_UP).doubleValue();
		double uah_price = new BigDecimal(original_price * rate04).setScale(2, RoundingMode.HALF_UP).doubleValue();
		double rub_price = new BigDecimal(original_price * rate05).setScale(2, RoundingMode.HALF_UP).doubleValue();

		String ip_Euro = "88.191.179.56";
		String ip_Yuan = "61.135.248.220";
		String ip_Pound = "92.40.254.196";
		String ip_Hryvnia = "93.183.203.67";
		String ip_Ruble = "213.87.141.36";

		String geoplag = "geoplugin_countryName";

		String country_name1 = CurrencyCode.getGeoValue(ip_Euro, geoplag);
		String country_name2 = CurrencyCode.getGeoValue(ip_Yuan, geoplag);
		String country_name3 = CurrencyCode.getGeoValue(ip_Pound, geoplag);
		String country_name4 = CurrencyCode.getGeoValue(ip_Hryvnia, geoplag);
		String country_name5 = CurrencyCode.getGeoValue(ip_Ruble, geoplag);

		String geoplagsign = "geoplugin_currencySymbol_UTF8";

		String currency_symbol1 = CurrencyCode.getGeoValue(ip_Euro, geoplagsign);
		String currency_symbol2 = CurrencyCode.getGeoValue(ip_Yuan, geoplagsign);
		String currency_symbol3 = CurrencyCode.getGeoValue(ip_Pound, geoplagsign);
		String currency_symbol4 = CurrencyCode.getGeoValue(ip_Hryvnia, geoplagsign);
		String currency_symbol5 = CurrencyCode.getGeoValue(ip_Ruble, geoplagsign);

		System.out.println("Item: " + title + "; " + "US Price: " + us_currency_symbol + original_price + "; "
				+ "for country: " + country_name1 + "; " + "Local Price: " + currency_symbol1 + eur_price);

		System.out.println("Item: " + title + "; " + "US Price: " + us_currency_symbol + original_price + "; "
				+ "for country: " + country_name2 + "; " + "Local Price: " + currency_symbol2 + cny_price);

		System.out.println("Item: " + title + "; " + "US Price: " + us_currency_symbol + original_price + "; "
				+ "for country: " + country_name3 + "; " + "Local Price: " + currency_symbol3 + gbp_price);

		System.out.println("Item: " + title + "; " + "US Price: " + us_currency_symbol + original_price + "; "
				+ "for country: " + country_name4 + "; " + "Local Price: " + currency_symbol4 + uah_price);

		System.out.println("Item: " + title + "; " + "US Price: " + us_currency_symbol + original_price + "; "
				+ "for country: " + country_name5 + "; " + "Local Price: " + currency_symbol5 + rub_price);

		driver.quit();
	}
}

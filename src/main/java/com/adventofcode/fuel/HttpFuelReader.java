package com.adventofcode.fuel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.eclipse.january.dataset.Dataset;

/**
 * Reads the dataset of fuel from a URL.
 * 
 * @author matt
 *
 */
public class HttpFuelReader {
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws URISyntaxException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public Dataset read(String url) throws URISyntaxException, IOException, InterruptedException {
		return read(new URL(url));
	}

	/**
	 * 
	 * @param fuelList
	 * @return
	 * @throws URISyntaxException
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public Dataset read(URL fuelList) throws URISyntaxException, IOException, InterruptedException {
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			      .uri(fuelList.toURI())
			      .timeout(Duration.ofSeconds(30))
			      .GET()
			      .build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String fuelsString = response.body();
		
		// Could parse HTTP output however puzzle is expected to be hard coded
		// because each user has a different list of fuels to calculate.
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		HttpFuelReader reader = new HttpFuelReader();
		reader.read("https://adventofcode.com/2019/day/1/input");
	}
}

package com.adventofcode.fuel;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * A simple test of FuelCalculator. We will not overengineer this 
 * because the tests are light.
 * 
 * @author matt
 *
 */
public class FuelCalculatorTest {

	private FuelCalculator calculator;

	@Before
	public void create() {
		this.calculator = new FuelCalculator();
	}
	
	@Test(expected=NumberFormatException.class)
	public void nan() {
		calculator.calculate(Double.NaN);
	}
	
	// Zero mass, zero fuel, that's okay
	public void zero() {
		assertEquals(0L, calculator.calculate(0L));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void negative() {
		calculator.calculate(-12);
	}

	@Test(expected=NumberFormatException.class)
	public void posinfinite() {
		calculator.calculate(Double.POSITIVE_INFINITY);
	}
	
	@Test(expected=NumberFormatException.class)
	public void neginfinite() {
		calculator.calculate(Double.NEGATIVE_INFINITY);
	}

	@Test
	public void twelve() {
		assertEquals(2, calculator.calculate(12));
	}
	
	@Test
	public void fourteen() {
		assertEquals(2, calculator.calculate(14));
	}
	
	@Test
	public void mass_1969() {
		assertEquals(654, calculator.calculate(1969));
	}
	
	@Test
	public void mass_100756() {
		assertEquals(33583, calculator.calculate(100756));
	}

	@Test
	public void masses1() {
		// See https://adventofcode.com/2019/day/1/input
		Dataset masses = DatasetFactory.createFromObject(new double[] {
				70102,
				60688,
				105331,
				127196,
				141253,
				118164,
				67481,
				75634,
				60715,
				84116,
				51389,
				52440,
				84992,
				87519,
				85765,
				124479
		});
		
		assertEquals(465717, calculator.calculate(masses).longValue());
	}
	
	@Test
	public void masses2() {
		// See https://adventofcode.com/2019/day/1/input
		Dataset masses = DatasetFactory.createFromObject(new int[] {
				0,
				70102,
				60688,
				105331,
				127196,
				141253,
				118164,
				67481,
				75634,
				60715,
				84116,
				51389,
				52440,
				84992,
				87519,
				85765,
				124479,
				0
		});
		
		assertEquals(465717, calculator.calculate(masses).longValue());
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void masses_neg() {
		// See https://adventofcode.com/2019/day/1/input
		Dataset masses = DatasetFactory.createFromObject(new int[] {
				70102,
				60688,
				-1,
				127196,
				141253,
				118164,
				67481,
				75634,
				60715,
				84116,
				51389,
				52440,
				84992,
				87519,
				85765,
				124479
		});
		
		calculator.calculate(masses);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void masses_inf() {
		// See https://adventofcode.com/2019/day/1/input
		Dataset masses = DatasetFactory.createFromObject(new double[] {
				70102,
				60688,
				105331,
				127196,
				141253,
				118164,
				67481,
				75634,
				60715,
				84116,
				51389,
				52440,
				Double.POSITIVE_INFINITY,
				87519,
				85765,
				124479
		});
		
		calculator.calculate(masses);
	}

	@Test
	public void masses_gerring_part1() throws IOException {
		
		URL url = getClass().getResource("module_masses.txt");
		// See https://adventofcode.com/2019/day/1/input  but it is different for every user
		List<String> smasses = IOUtils.readLines(url.openStream(), "UTF-8");
		List<Double> dmasses = smasses.stream().map(s->Double.parseDouble(s)).collect(Collectors.toList());
		Dataset masses = DatasetFactory.createFromObject(dmasses);
		
		assertEquals(3295206, calculator.calculate(masses).longValue());
	}
	
	@Test
	public void masses_recursive1() throws IOException {
				
		assertEquals(2, calculator.calculateRecursive(14));
	}
	
	@Test
	public void masses_recursive2() throws IOException {
				
		assertEquals(966, calculator.calculateRecursive(1969));
	}

	@Test
	public void masses_recursive3() throws IOException {
				
		assertEquals(50346, calculator.calculateRecursive(100756));
	}
	
	@Test
	public void masses_gerring_part2() throws IOException {
		
		URL url = getClass().getResource("module_masses.txt");
		// See https://adventofcode.com/2019/day/1/input  but it is different for every user
		List<String> smasses = IOUtils.readLines(url.openStream(), "UTF-8");
		List<Double> dmasses = smasses.stream().map(s->Double.parseDouble(s)).collect(Collectors.toList());
		Dataset masses = DatasetFactory.createFromObject(dmasses);
		
		assertEquals(4939939, calculator.calculateRecursive(masses).longValue());
	}

}

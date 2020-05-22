package com.adventofcode.fuel;

import org.eclipse.dawnsci.analysis.dataset.DatasetStreamSupport;
import org.eclipse.january.dataset.Dataset;

/**
 * Calculator for fuel 
 * 
 * We use Eclipse January for this because it is awesome.
 */
public class FuelCalculator {

	public FuelCalculator() {
		
	}
	
	/**
	 * Calculate the integer fuel requirement from a
	 * dataset of module masses.
	 * 
	 * @param masses - module masses
	 * @return fuel requirement in kg
	 */
	public Number calculate(Dataset masses) {
		// This limits the max fuel to long. That is probably more fuel than the world has
		// depdning on the unit chosen, so it should be okay.
		return DatasetStreamSupport.createDatasetDoubleStream(masses, false).mapToLong(m->calculate(m)).sum();
	}

	/**
	 * Calculate the fuel for a given module mass.
	 * @param mass in kg
	 * @return fuel mass in kg
	 */
	public long calculate(double mass) {
		if (Double.isNaN(mass) || !Double.isFinite(mass))  {
			throw new NumberFormatException("Mass must be a finite positive real number!");
		}
		if (mass<0) throw new IllegalArgumentException("Mass must be positive!");
		if (mass==0)  return 0; // 0kg costs 0 fuel, that's okay
		return Math.round(Math.floor(mass/3.0)-2);
	}
}

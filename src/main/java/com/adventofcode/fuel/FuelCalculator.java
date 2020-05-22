package com.adventofcode.fuel;

import org.eclipse.dawnsci.analysis.dataset.DatasetStreamSupport;
import org.eclipse.january.dataset.Dataset;

/**
 * Calculator for fuel 
 * 
 * We use Eclipse January for this because it is awesome.
 * 
 * Printed below are the questions

--- Day 1: The Tyranny of the Rocket Equation ---
Santa has become stranded at the edge of the Solar System while delivering presents to other planets! To accurately calculate his position in space, safely align his warp drive, and return to Earth in time to save Christmas, he needs you to bring him measurements from fifty stars.

Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!

The Elves quickly load you into a spacecraft and prepare to launch.

At the first Go / No Go poll, every Elf is Go until the Fuel Counter-Upper. They haven't determined the amount of fuel required yet.

Fuel required to launch a given module is based on its mass. Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.

For example:

For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
For a mass of 1969, the fuel required is 654.
For a mass of 100756, the fuel required is 33583.
The Fuel Counter-Upper needs to know the total fuel requirement. To find it, individually calculate the fuel needed for the mass of each module (your puzzle input), then add together all the fuel values.

What is the sum of the fuel requirements for all of the modules on your spacecraft?

Your puzzle answer was 3295206.

The first half of this puzzle is complete! It provides one gold star: *

--- Part Two ---
During the second Go / No Go poll, the Elf in charge of the Rocket Equation Double-Checker stops the launch sequence. Apparently, you forgot to include additional fuel for the fuel you just added.

Fuel itself requires fuel just like a module - take its mass, divide by three, round down, and subtract 2. However, that fuel also requires fuel, and that fuel requires fuel, and so on. Any mass that would require negative fuel should instead be treated as if it requires zero fuel; the remaining mass, if any, is instead handled by wishing really hard, which has no mass and is outside the scope of this calculation.

So, for each module mass, calculate its fuel and add it to the total. Then, treat the fuel amount you just calculated as the input mass and repeat the process, continuing until a fuel requirement is zero or negative. For example:

A module of mass 14 requires 2 fuel. This fuel requires no further fuel (2 divided by 3 and rounded down is 0, which would call for a negative fuel), so the total fuel required is still just 2.
At first, a module of mass 1969 requires 654 fuel. Then, this fuel requires 216 more fuel (654 / 3 - 2). 216 then requires 70 more fuel, which requires 21 fuel, which requires 5 fuel, which requires no further fuel. So, the total fuel required for a module of mass 1969 is 654 + 216 + 70 + 21 + 5 = 966.
The fuel required by a module of mass 100756 and its fuel is: 33583 + 11192 + 3728 + 1240 + 411 + 135 + 43 + 12 + 2 = 50346.
What is the sum of the fuel requirements for all of the modules on your spacecraft when also taking into account the mass of the added fuel? (Calculate the fuel requirements for each module separately, then add them all up at the end.)

 */
public class FuelCalculator {

	public FuelCalculator() {
		
	}
	
	/**
	 * Calculate the integer fuel requirement from a
	 * dataset of module masses.
	 * 
	 * This method calculates the fuel for the modules and the fuel
	 * for the fuel recursively. It returns the complete fuel requirement.
	 * 
	 * @param masses - module masses
	 * @return fuel requirement in kg
	 */
	public Number calculateRecursive(Dataset masses) {
		// This limits the max fuel to long. That is probably more fuel than the world has
		// dependning on the unit chosen, so it should be okay.
		return DatasetStreamSupport.createDatasetDoubleStream(masses, false).mapToLong(m->calculateRecursive(m)).sum();
	}

	/**
	 * Calculate the integer fuel requirement from a
	 * dataset of module masses.
	 * 
	 * This method calculates the fuel for the modules and the fuel
	 * for the fuel recursively. It returns the complete fuel requirement.
	 * 
	 * @param masses - module masses
	 * @return fuel requirement in kg
	 */
	public long calculateRecursive(double mass) {
		double fuel = calculate(mass);
		long total = 0;
		total+=fuel;
		
		// Actually a loop was easier than recursion because of the 
		// way that part 1 was solved.
		while((fuel =  calculate(fuel)) > 0) {
			total+=fuel;
		}
		return total;
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
		// dependning on the unit chosen, so it should be okay.
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

/**
* Tests RangesOfNumericValuesWithWeights class.
* 
* @author Pedro Lívio da Silva Barbosa
* @version 1.0, 24 August 2021
*/
package br.com.plisbarbosa.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class RangesOfNumericValuesWithWeightsTest {
	@Test
	public void splitTheValue() throws Exception {
		RangesOfNumericValuesWithWeights rangesOfNumericValuesWithWeights = new RangesOfNumericValuesWithWeights.Builder()
				.rangeCeilings(new HashMap<>()).rangeRoofs(new HashMap<>()).rangeWeights(new HashMap<>()).build();

		rangesOfNumericValuesWithWeights.getRangeCeilings().put(1, 0.00);
		rangesOfNumericValuesWithWeights.getRangeCeilings().put(2, 2000.00);
		rangesOfNumericValuesWithWeights.getRangeCeilings().put(3, 3000.00);
		rangesOfNumericValuesWithWeights.getRangeCeilings().put(4, 4500.00);

		rangesOfNumericValuesWithWeights.getRangeRoofs().put(1, 2000.00);
		rangesOfNumericValuesWithWeights.getRangeRoofs().put(2, 3000.00);
		rangesOfNumericValuesWithWeights.getRangeRoofs().put(3, 4500.00);
		rangesOfNumericValuesWithWeights.getRangeRoofs().put(4, Double.MAX_VALUE);

		rangesOfNumericValuesWithWeights.getRangeWeights().put(1, 0.00);
		rangesOfNumericValuesWithWeights.getRangeWeights().put(2, 0.08);
		rangesOfNumericValuesWithWeights.getRangeWeights().put(3, 0.18);
		rangesOfNumericValuesWithWeights.getRangeWeights().put(4, 0.28);

		HashMap<Integer, Double> desiredRangeValues = new HashMap<>();

		desiredRangeValues.put(1, 2000.00);
		desiredRangeValues.put(2, 1000.00);
		desiredRangeValues.put(3, 2.00);
		desiredRangeValues.put(4, 0.00);
		Map<Integer, Double> rangeValues = rangesOfNumericValuesWithWeights.splitTheValue(3002.00);
		assertTrue(rangeValues.equals(desiredRangeValues));

		desiredRangeValues.clear();
		desiredRangeValues.put(1, 1701.12);
		desiredRangeValues.put(2, 0.00);
		desiredRangeValues.put(3, 0.00);
		desiredRangeValues.put(4, 0.00);
		rangeValues = rangesOfNumericValuesWithWeights.splitTheValue(1701.12);
		assertTrue(rangeValues.equals(desiredRangeValues));

		desiredRangeValues.clear();
		desiredRangeValues.put(1, 2000.00);
		desiredRangeValues.put(2, 1000.00);
		desiredRangeValues.put(3, 1500.00);
		desiredRangeValues.put(4, 20.00);
		rangeValues = rangesOfNumericValuesWithWeights.splitTheValue(4520.00);
		assertTrue(rangeValues.equals(desiredRangeValues));
	}

	@Test
	public void sumAllRangeValues() throws Exception {
		RangesOfNumericValuesWithWeights rangesOfNumericValuesWithWeights = new RangesOfNumericValuesWithWeights.Builder()
				.rangeCeilings(new HashMap<>()).rangeRoofs(new HashMap<>()).rangeWeights(new HashMap<>()).build();

		rangesOfNumericValuesWithWeights.getRangeCeilings().put(1, 0.00);
		rangesOfNumericValuesWithWeights.getRangeCeilings().put(2, 2000.00);
		rangesOfNumericValuesWithWeights.getRangeCeilings().put(3, 3000.00);
		rangesOfNumericValuesWithWeights.getRangeCeilings().put(4, 4500.00);

		rangesOfNumericValuesWithWeights.getRangeRoofs().put(1, 2000.00);
		rangesOfNumericValuesWithWeights.getRangeRoofs().put(2, 3000.00);
		rangesOfNumericValuesWithWeights.getRangeRoofs().put(3, 4500.00);
		rangesOfNumericValuesWithWeights.getRangeRoofs().put(4, Double.MAX_VALUE);

		rangesOfNumericValuesWithWeights.getRangeWeights().put(1, 0.00);
		rangesOfNumericValuesWithWeights.getRangeWeights().put(2, 0.08);
		rangesOfNumericValuesWithWeights.getRangeWeights().put(3, 0.18);
		rangesOfNumericValuesWithWeights.getRangeWeights().put(4, 0.28);

		Map<Integer, Double> rangeValues = rangesOfNumericValuesWithWeights.splitTheValue(3002.00);
		assertTrue(rangesOfNumericValuesWithWeights.sumAllRangeValues(rangeValues) == 80.36);

		rangeValues = rangesOfNumericValuesWithWeights.splitTheValue(1701.12);
		assertTrue(rangesOfNumericValuesWithWeights.sumAllRangeValues(rangeValues) == 00.00);

		rangeValues = rangesOfNumericValuesWithWeights.splitTheValue(4520.00);
		assertTrue(rangesOfNumericValuesWithWeights.sumAllRangeValues(rangeValues) == 355.60);
	}
}

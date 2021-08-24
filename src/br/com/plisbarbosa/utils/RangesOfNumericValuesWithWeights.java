/**
* Helps to solve problems involving ranges of numeric values with weights.
* 
* @author Pedro Lívio da Silva Barbosa
* @version 1.0, 24 August 2021
*/
package br.com.plisbarbosa.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.com.plisbarbosa.exceptions.AtLeastOneMapHasADifferentSizeException;
import br.com.plisbarbosa.exceptions.AtLeastOneMapHasDifferentKeysException;
import br.com.plisbarbosa.exceptions.AtLeastOneMapIsEmptyException;
import br.com.plisbarbosa.exceptions.AtLeastOneMapIsNullException;

public class RangesOfNumericValuesWithWeights implements Serializable {
	/**
	 * Separates the construction of a complex object from its representation so
	 * that the same construction process can create different representations.
	 */
	public static class Builder {
		private Map<Integer, Double> rangeCeilings;
		private Map<Integer, Double> rangeRoofs;
		private Map<Integer, Double> rangeWeights;

		/**
		 * Builds a RangesOfNumericValuesWithWeights object.
		 * 
		 * @return a RangesOfNumericValuesWithWeights object
		 */
		public RangesOfNumericValuesWithWeights build() {
			RangesOfNumericValuesWithWeights numericRangesAndWeights = new RangesOfNumericValuesWithWeights();

			numericRangesAndWeights.rangeCeilings = this.rangeCeilings;
			numericRangesAndWeights.rangeRoofs = this.rangeRoofs;
			numericRangesAndWeights.rangeWeights = this.rangeWeights;

			return numericRangesAndWeights;
		}

		public Builder rangeCeilings(Map<Integer, Double> rangeCeilings) {
			this.rangeCeilings = rangeCeilings;

			return this;
		}

		public Builder rangeRoofs(Map<Integer, Double> rangeRoofs) {
			this.rangeRoofs = rangeRoofs;

			return this;
		}

		public Builder rangeWeights(Map<Integer, Double> rangeWeights) {
			this.rangeWeights = rangeWeights;

			return this;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, Double> rangeCeilings;
	private Map<Integer, Double> rangeRoofs;
	private Map<Integer, Double> rangeWeights;

	public RangesOfNumericValuesWithWeights() {
		super();
	}

	public Map<Integer, Double> getRangeCeilings() {
		return rangeCeilings;
	}

	public Map<Integer, Double> getRangeRoofs() {
		return rangeRoofs;
	}

	public Map<Integer, Double> getRangeWeights() {
		return rangeWeights;
	}

	/**
	 * Splits the value on range values using the range floors and the range
	 * ceilings.
	 * 
	 * @return the range values from the value
	 * @throws AtLeastOneMapHasDifferentKeysException
	 * @throws AtLeastOneMapHasADifferentSizeException
	 * @throws AtLeastOneMapIsEmptyException
	 * @throws AtLeastOneMapIsNullException
	 */
	public Map<Integer, Double> splitTheValue(double value)
			throws AtLeastOneMapIsNullException, AtLeastOneMapIsEmptyException, AtLeastOneMapHasADifferentSizeException,
			AtLeastOneMapHasDifferentKeysException {
		this.validate();

		HashMap<Integer, Double> rangeValues = new HashMap<>();

		for (Integer i : this.rangeCeilings.keySet()) {
			if (value > this.rangeCeilings.get(i)) {
				if (value > this.rangeRoofs.get(i)) {
					rangeValues.put(i, (this.rangeRoofs.get(i) - this.rangeCeilings.get(i)));
				} else {
					rangeValues.put(i, (value - this.rangeCeilings.get(i)));
				}
			} else {
				rangeValues.put(i, 0.00);
			}
		}

		return rangeValues;
	}

	/**
	 * Sums all range values ​​multiplied by the respective range weights.
	 * 
	 * @return the sum of all range values ​​multiplied by the respective range
	 *         weights
	 * @throws AtLeastOneMapHasDifferentKeysException
	 * @throws AtLeastOneMapHasADifferentSizeException
	 * @throws AtLeastOneMapIsEmptyException
	 * @throws AtLeastOneMapIsNullException
	 */
	public double sumAllRangeValues(Map<Integer, Double> rangeValues)
			throws AtLeastOneMapIsNullException, AtLeastOneMapIsEmptyException, AtLeastOneMapHasADifferentSizeException,
			AtLeastOneMapHasDifferentKeysException {
		this.validate(rangeValues);

		double sumOfAllRangeValues​MultipliedByTheRespectiveRangeWeights = 0.00;

		for (Integer i : this.rangeCeilings.keySet()) {
			sumOfAllRangeValues​MultipliedByTheRespectiveRangeWeights += rangeValues.get(i) * this.rangeWeights.get(i);
		}

		return sumOfAllRangeValues​MultipliedByTheRespectiveRangeWeights;
	}

	/**
	 * Validates a RangesOfNumericValuesWithWeights object.
	 * 
	 * @throws AtLeastOneMapIsNullException            if rangeCeilings, rangeRoofs
	 *                                                 or rangeWeights is null
	 * @throws AtLeastOneMapIsEmptyException           if rangeCeilings, rangeRoofs
	 *                                                 or rangeWeights is empty
	 * @throws AtLeastOneMapHasADifferentSizeException if rangeCeilings, rangeRoofs
	 *                                                 or rangeWeights sizes are not
	 *                                                 the same
	 * @throws AtLeastOneMapHasDifferentKeysException  if rangeCeilings, rangeRoofs
	 *                                                 or rangeWeights keys are not
	 *                                                 the same
	 */
	private void validate() throws AtLeastOneMapIsNullException, AtLeastOneMapIsEmptyException,
			AtLeastOneMapHasADifferentSizeException, AtLeastOneMapHasDifferentKeysException {
		if (this.rangeCeilings == null || this.rangeRoofs == null || this.rangeWeights == null) {
			throw new AtLeastOneMapIsNullException();
		}

		if (this.rangeCeilings.isEmpty() || this.rangeRoofs.isEmpty() || this.rangeWeights.isEmpty()) {
			throw new AtLeastOneMapIsEmptyException();
		}

		if ((this.rangeCeilings.size() != this.rangeRoofs.size())
				|| (this.rangeRoofs.size() != this.rangeWeights.size())) {
			throw new AtLeastOneMapHasADifferentSizeException();
		}

		if (!this.rangeCeilings.keySet().equals(this.rangeRoofs.keySet())
				|| !this.rangeRoofs.keySet().equals(this.rangeWeights.keySet())) {
			throw new AtLeastOneMapHasDifferentKeysException();
		}
	}

	/**
	 * Validates a RangesOfNumericValuesWithWeights object.
	 * 
	 * @throws AtLeastOneMapIsNullException            if rangeValues,
	 *                                                 rangeCeilings, rangeRoofs or
	 *                                                 rangeWeights are null
	 * @throws AtLeastOneMapIsEmptyException           if rangeValues,
	 *                                                 rangeCeilings, rangeRoofs or
	 *                                                 rangeWeights are empty
	 * @throws AtLeastOneMapHasADifferentSizeException if rangeValues,
	 *                                                 rangeCeilings, rangeRoofs or
	 *                                                 rangeWeights sizes are not
	 *                                                 the same
	 * @throws AtLeastOneMapHasDifferentKeysException  if rangeValues,
	 *                                                 rangeCeilings, rangeRoofs or
	 *                                                 rangeWeights keys are not the
	 *                                                 same
	 */
	private void validate(Map<Integer, Double> rangeValues)
			throws AtLeastOneMapIsNullException, AtLeastOneMapIsEmptyException, AtLeastOneMapHasADifferentSizeException,
			AtLeastOneMapHasDifferentKeysException {
		if (rangeValues == null || this.rangeCeilings == null || this.rangeRoofs == null || this.rangeWeights == null) {
			throw new AtLeastOneMapIsNullException();
		}

		if (rangeValues.isEmpty() || this.rangeCeilings.isEmpty() || this.rangeRoofs.isEmpty()
				|| this.rangeWeights.isEmpty()) {
			throw new AtLeastOneMapIsEmptyException();
		}

		if ((rangeValues.size() != rangeCeilings.size()) || (this.rangeCeilings.size() != this.rangeRoofs.size())
				|| (this.rangeRoofs.size() != this.rangeWeights.size())) {
			throw new AtLeastOneMapHasADifferentSizeException();
		}

		if (!rangeValues.keySet().equals(this.rangeCeilings.keySet())
				|| !this.rangeCeilings.keySet().equals(this.rangeRoofs.keySet())
				|| !this.rangeRoofs.keySet().equals(this.rangeWeights.keySet())) {
			throw new AtLeastOneMapHasDifferentKeysException();
		}
	}
}

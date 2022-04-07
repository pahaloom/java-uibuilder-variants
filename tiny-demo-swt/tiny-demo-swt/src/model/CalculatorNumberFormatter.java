package model;

import java.text.DecimalFormat;

import org.eclipse.core.databinding.conversion.Converter;

public class CalculatorNumberFormatter extends Converter<Float, String> {
	DecimalFormat format = new DecimalFormat("0.#######");

	public CalculatorNumberFormatter() {
		super(Float.class, String.class);
	}

	@Override
	public String convert(Float num) {
		if (num == null) {
			return null;
		}
		return format.format(num.doubleValue());
	}
}

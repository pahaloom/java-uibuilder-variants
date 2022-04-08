package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import pahaloom.tinycalc.Operation;
import static pahaloom.tinycalc.Operation.*;

public class CalculatorViewModelBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int a;
	private int b;
	private Float ans;
	private List<Operation> operations = Arrays
			.asList(new Operation[] { new OpAdd(), new OpSub(), new OpMul(), new OpDiv() });

	private int opIndex;

	public void calculate() {
		if (opIndex < operations.size() && opIndex >= 0) {
			setAns(operations.get(opIndex).operate(a, b));
		}
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getOpIndex() {
		return opIndex;
	}

	public void setOpIndex(int opIndex) {
		this.opIndex = opIndex;
	}

	public Float getAns() {
		return ans;
	}

	public void setAns(Float ans) {
		this.ans = ans;
	}
}

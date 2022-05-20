package pahaloom.tinycalc;

public abstract class Operation {
	private final String title;

	public Operation(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}

	public abstract float operate(int a, int b);


	public static class OpAdd extends Operation {
		public OpAdd() {
			super("+");
		}

		public float operate(int a, int b) {
			return a + b;
		}
	}

	public static class OpSub extends Operation {
		public OpSub() {
			super("-");
		}

		public float operate(int a, int b) {
			return (float) a - b;
		}
	}

	public static class OpMul extends Operation {
		public OpMul() {
			super("*");
		}

		public float operate(int a, int b) {
			return (float) a * b;
		}
	}

	public static class OpDiv extends Operation {
		public OpDiv() {
			super("/");
		}

		public float operate(int a, int b) {
			return (float) a / b;
		}
	}
}

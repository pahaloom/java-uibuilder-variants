module pahaloom.graphics.calculator.fglrx {
    requires javafx.controls;
    requires javafx.fxml;
	requires tinycalculator;

    opens pahaloom.graphics.calculator.fglrx to javafx.fxml;
    exports pahaloom.graphics.calculator.fglrx;
}

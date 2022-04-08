module fxcalculator {
	requires javafx.controls;
	requires javafx.fxml;
	requires tinycalculator;
	
	opens pahaloom.tinyfxapp to javafx.graphics, javafx.fxml;
}

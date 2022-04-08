package pahaloom.tinyfxapp;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import pahaloom.tinycalc.Operation;

public class FXCalculatorController {
	@FXML TextField valA;
	@FXML TextField valB;
	@FXML ChoiceBox<Operation> op;
	@FXML TextField valC;

	@FXML
	public void initialize() {
		ObservableList<Operation> observableOperations = FXCollections.observableList(Arrays.asList(new Operation[] {
				new Operation.OpAdd(),
				new Operation.OpSub(),
				new Operation.OpMul(),
				new Operation.OpDiv()
		}));
		op.setItems(observableOperations);
		op.setOnAction(e -> {
			valC.setText("" + op.getValue().operate(toInt(valA.getText()), toInt(valB.getText())));
		});
	}
	static int toInt(String txt) {
		return Integer.parseInt(txt);
	}
}

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import model.CalculatorViewModelBean;
import model.CalculatorNumberFormatter;

public class SWTCalculator {
	private Binding ansBinding;
	private DataBindingContext m_bindingContext;

	private CalculatorViewModelBean backingBean = new CalculatorViewModelBean();

	protected Shell shell;
	private Text aField;
	private Text bField;
	private Text ansField;
	private Combo opCombo;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					SWTCalculator window = new SWTCalculator();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}


	private void selectAll(Text source) {
		String text = source.getText();
		if (text != null && text.length() > 0) {
			source.setSelection(0, text.length());
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(215, 219);
		shell.setText("SWTCalculator");
		shell.setLayout(new FillLayout(SWT.VERTICAL));

		aField = new Text(shell, SWT.BORDER | SWT.RIGHT);
		aField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				selectAll((Text) e.getSource());
			}
		});
		aField.setFont(SWTResourceManager.getFont("Ubuntu", 12, SWT.NORMAL));

		bField = new Text(shell, SWT.BORDER | SWT.RIGHT);
		bField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				selectAll((Text) e.getSource());
			}
		});
		bField.setFont(SWTResourceManager.getFont("Ubuntu", 12, SWT.NORMAL));

		opCombo = new Combo(shell, SWT.NONE);
		opCombo.setFont(SWTResourceManager.getFont("Ubuntu", 12, SWT.NORMAL));

		Button calcButton = new Button(shell, SWT.NONE);
		calcButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				backingBean.calculate();
				ansBinding.updateModelToTarget();
			}
		});
		calcButton.setText("Calculate");

		ansField = new Text(shell, SWT.BORDER | SWT.RIGHT);
		ansField.setFont(SWTResourceManager.getFont("Ubuntu", 12, SWT.NORMAL));
		m_bindingContext = initDataBindings();

	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextAFieldObserveWidget = WidgetProperties.text(SWT.Modify).observe(aField);
		IObservableValue aBackingBeanObserveValue = PojoProperties.value("a").observe(backingBean);
		bindingContext.bindValue(observeTextAFieldObserveWidget, aBackingBeanObserveValue, null, null);
		//
		IObservableValue observeTextBFieldObserveWidget = WidgetProperties.text(SWT.Modify).observe(bField);
		IObservableValue bBackingBeanObserveValue = PojoProperties.value("b").observe(backingBean);
		bindingContext.bindValue(observeTextBFieldObserveWidget, bBackingBeanObserveValue, null, null);
		//
		IObservableList itemsOpComboObserveWidget = WidgetProperties.items().observe(opCombo);
		IObservableList operationsBackingBeanObserveList = PojoProperties.list("operations").observe(backingBean);
		bindingContext.bindList(itemsOpComboObserveWidget, operationsBackingBeanObserveList, null, null);
		//
		IObservableValue observeTextAnsFieldObserveWidget = WidgetProperties.text(SWT.Modify).observe(ansField);
		IObservableValue ansBackingBeanObserveValue = PojoProperties.value("ans").observe(backingBean);
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setConverter(new CalculatorNumberFormatter());
		ansBinding = bindingContext.bindValue(observeTextAnsFieldObserveWidget, ansBackingBeanObserveValue, new UpdateValueStrategy(UpdateValueStrategy.POLICY_ON_REQUEST), strategy);
		//
		IObservableValue observeSingleSelectionIndexOpComboObserveWidget = WidgetProperties.singleSelectionIndex().observe(opCombo);
		IObservableValue opIndexBackingBeanObserveValue = PojoProperties.value("opIndex").observe(backingBean);
		bindingContext.bindValue(observeSingleSelectionIndexOpComboObserveWidget, opIndexBackingBeanObserveValue, null, null);
		//
		return bindingContext;
	}
}

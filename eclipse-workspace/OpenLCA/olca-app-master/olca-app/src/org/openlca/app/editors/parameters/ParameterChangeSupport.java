package org.openlca.app.editors.parameters;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows communication between a parameter page (with sections) and other
 * editor pages.
 */
public class ParameterChangeSupport {

	private final List<Runnable> evaluators = new ArrayList<>();
	private final List<Runnable> observers = new ArrayList<>();

	public void onEvaluation(Runnable fn) {
		if (fn != null)
			evaluators.add(fn);
	}

	public void afterEvaluation(Runnable fn) {
		if (fn != null)
			observers.add(fn);
	}

	public void evaluate() {
		for (Runnable evaluator : evaluators)
			evaluator.run();
		for (Runnable observer : observers)
			observer.run();
	}

}

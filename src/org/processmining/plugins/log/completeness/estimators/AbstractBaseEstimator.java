package org.processmining.plugins.log.completeness.estimators;

/**
 * The basic class for the estimators.
 * 
 * @author hedong
 * 
 */
abstract class AbstractBaseEstimator {

	private String name;

	public AbstractBaseEstimator(String name) {
		this.name = name;
	}

	public AbstractBaseEstimator() {
		this.name = getClass().getSimpleName();
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}
}
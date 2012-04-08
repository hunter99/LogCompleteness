package org.processmining.plugins.log.completeness.estimators;

import org.processmining.plugins.completeness.annotations.CoverageEstimator;
import org.processmining.plugins.completeness.annotations.UnobservedClassesEstimator;
import org.processmining.plugins.log.completeness.StatRes;

/**
 * To estimate the magnitude of local informative completeness of an event log.
 */
@CoverageEstimator
@UnobservedClassesEstimator
public class LCPL1Estimator extends AbstractCompletenessEstimatorV1 {
	public final static double CONFIDENCE = 0.9;
	public final static double EPSILON = 0.1;

	public LCPL1Estimator() {
		this(EPSILON, CONFIDENCE);
	}

	public LCPL1Estimator(double e, double c) {
		super("LCPL1",e,c);
	}

	public long getNumOfObservedUnits(StatRes res) {
		return res.getDSFreqs().size();
	}

}


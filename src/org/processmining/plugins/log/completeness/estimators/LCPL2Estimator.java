package org.processmining.plugins.log.completeness.estimators;

import org.processmining.plugins.completeness.annotations.CoverageEstimator;
import org.processmining.plugins.completeness.annotations.UnobservedClassesEstimator;
import org.processmining.plugins.log.completeness.StatRes;

/**
 * To estimate the magnitude of local informative completeness of an event log.
 * <P>
 * Proposed in our paper.
 */
@CoverageEstimator
@UnobservedClassesEstimator
public class LCPL2Estimator extends AbstractCompletenessEstimatorV2 {
	public final static double CONFIDENCE = 0.9;
	public final static double EPSILON = 0.1;

	public LCPL2Estimator() {
		this(EPSILON, CONFIDENCE);
	}

	public LCPL2Estimator(double e, double c) {
		super("LCPL2",e,c);
	}

	public long getNumOfObservedUnits(StatRes res) {
		return res.getDSFreqs().size();
	}

}

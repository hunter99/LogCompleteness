package org.processmining.plugins.log.completeness.estimators;

import org.processmining.plugins.completeness.annotations.CoverageEstimator;
import org.processmining.plugins.completeness.annotations.UnobservedClassesEstimator;
import org.processmining.plugins.log.completeness.StatRes;

/**
 * To estimate the magnitude of global informative completeness of an event log.
 * <P>
 * Proposed in our paper.
 */
@CoverageEstimator
@UnobservedClassesEstimator
public class GCPL2Estimator extends AbstractCompletenessEstimatorV2 {
	public final static double CONFIDENCE = 0.9;
	public final static double EPSILON = 0.1;

	public GCPL2Estimator() {
		this(EPSILON, CONFIDENCE);
	}

	public GCPL2Estimator(double e, double c) {
		super("GCPL2",e,c);
	}

	public long getNumOfObservedUnits(StatRes res) {
		return res.getTraces().size();
	}

}

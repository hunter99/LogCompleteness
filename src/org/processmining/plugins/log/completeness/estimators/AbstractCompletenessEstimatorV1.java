package org.processmining.plugins.log.completeness.estimators;

import org.processmining.plugins.log.completeness.StatRes;


/**
 * The first version of our approach to estimate the informative completeness.
 * <P> N>=W^3/(4e^2(1-k))
 * 
 * @author hedong
 *
 */
public abstract class AbstractCompletenessEstimatorV1 extends AbstractComletenessEstimator {
	public AbstractCompletenessEstimatorV1(String name,double e, double c) {
		super(name,e,c);
	}

	public void calculate(double epsilone, double confidence, long numOfObservedUnits, long loglength, StatRes res) {
		double divisor = 4 * epsilon * epsilon * (1 - confidence);
		double dividend = numOfObservedUnits * numOfObservedUnits * numOfObservedUnits;
		if(divisor == 0){
			res.setCoverageProbability(-1);
			res.setNewClassProbability(-1);
			return;
		}
		// a lower bound of the expected log length, given number of trace classes(W), error rate (epsilon), and confidence level(K).
		//long expectedlen = (long) Math.ceil(dividend / divisor);
		res.setExpectedCompleteLogLength((long) Math.ceil(dividend / divisor));
		double divisor2 = 2 * Math.sqrt(loglength * (1 - confidence));
		double dividend2 = numOfObservedUnits * Math.sqrt(numOfObservedUnits);
		if(divisor2 == 0){
			res.setCoverageProbability(-1);
			res.setNewClassProbability(-1);
			return;
		}
		// a lower bound of informative completeness of a log, given number of trace classes(W), log length(N), and confidence level(K).
		//double info = 1 - dividend2 / divisor2;
		res.setCoverageProbability(1 - dividend2 / divisor2);
		if (res.getCoverageProbability() < 0 || res.getCoverageProbability() > 1) {
			res.setCoverageProbability(-1);
			res.setNewClassProbability(-1);
		} else {
			res.setNewClassProbability(1 - res.getCoverageProbability());
		}		
	}

}

package org.processmining.plugins.log.completeness.estimators;

import org.processmining.plugins.log.completeness.Estimator;
import org.processmining.plugins.log.completeness.StatRes;

/**
 * Our approach to estimate the informative the completeness of an event log.
 * 
 * @author hedong
 *
 */
public abstract class AbstractComletenessEstimator extends AbstractBaseEstimator  implements Estimator {

	public AbstractComletenessEstimator(String name,double e, double c) {
		super(name);
		setEpsilon(e);
		setConfidence(c);
	}

	/**
	 * epsilon, the maximum acceptable error of the estimation.
	 * <p>
	 * The default value is 0.1. It can be reset by calling setEpsilon(ep) at
	 * running time.
	 */
	double epsilon = 0.1;
	/**
	 * Confidence level.
	 * <p>
	 * The default value is 0.95. It can be reset by calling setConfidence(cf)
	 * at running time.
	 */
	double confidence = 0.95;

	/**
	 * Set the value of epsilon.
	 * 
	 * @param ep
	 *            the value of epsilon.
	 */
	public void setEpsilon(double ep) {
		epsilon = ep;
	}

	/**
	 * Set the value of confidence level.
	 * 
	 * @param cf
	 *            the value of confidence level.
	 */
	public void setConfidence(double cf) {
		confidence = cf;
	}
	public double getConfidence(){
		return confidence;
	}
	public double getEpsilon(){
		return epsilon;
	}
	public void estimate(StatRes res){
		//check the validity of epsilon value.
		if (epsilon <= 0 || epsilon >= 1) {
			//			System.out.println("epsilon should be in (0,1)");
			res.setCoverageProbability(-1);
			res.setNewClassProbability(-1);
			return;
		}
		if (confidence <= 0 || confidence >= 1) {
			//			System.out.println("confidence should be in (0,1)");
			res.setCoverageProbability(-1);
			res.setNewClassProbability(-1);
			return;
		}
		long loglength=res.getLoglength();
		long numOfObservedUnits=getNumOfObservedUnits(res);
		if (1.0 * numOfObservedUnits / loglength > 0.4) {
			//			System.out.println("\t\t\tM/N%:" + 100 * numOfObservedUnits / loglength);
			res.setCoverageProbability(-1);
			res.setNewClassProbability(-1);
			return;
		}
		calculate(epsilon, confidence, numOfObservedUnits, loglength, res);
	}
	/**
	 * Calculate the number of inofrmation units in the log.
	 * @param res
	 * @return
	 */
	public abstract long getNumOfObservedUnits(StatRes res);
	/**
	 * calculate the estimation values.
	 * @param epsilone
	 * @param confidence
	 * @param numOfObservedUnits
	 * @param loglength
	 * @param res
	 */
	public abstract void calculate(double epsilone, double confidence, long numOfObservedUnits, long loglength, StatRes res) ;
}

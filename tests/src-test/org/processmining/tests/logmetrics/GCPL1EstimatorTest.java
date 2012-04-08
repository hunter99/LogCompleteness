package org.processmining.tests.logmetrics;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.processmining.plugins.log.completeness.StatRes;
import org.processmining.plugins.log.completeness.estimators.GCPL1Estimator;

public class GCPL1EstimatorTest {
	GCPL1Estimator gcpl1;
	StatRes res;
	double delta=Math.pow(0.1, 10);
	@Before
	public void setUp() throws Exception {
		gcpl1 = new GCPL1Estimator();
		res =new StatRes(null,0,null);
	}

	@After
	public void tearDown() throws Exception {
		gcpl1 = null;
	}

	@Test
	public final void testGetNumOfObservedUnits() {
		assertEquals(gcpl1.getNumOfObservedUnits(res),res.getLoglength());
	}

	@Test
	public final void testEstimate() {
		res.initial4Estimating();
		gcpl1.estimate(res);
		System.out.println(res.getCoverageProbability());
		assertEquals(res.getCoverageProbability(),-1.0,delta);
		assertEquals(res.getCoverageProbability(),-1.0,delta);
	}
	@Test
	public final void testCalculate() {
		res.initial4Estimating();
		gcpl1.calculate(0.1,0.9,10,10000, res);		;
		assertEquals(res.getExpectedCompleteLogLength(),Math.pow(10, 3)/(4*Math.pow(0.1,3)),delta);
//		assertEquals(res.getNewClassProbability(),1.0-res.getCoverageProbability(),delta);
	}
	
	@Test
	public final void testSetEpsilon() {
		assertEquals(gcpl1.getEpsilon(),0.1,delta);
	}

	@Test
	public final void testSetConfidence() {
		assertEquals(gcpl1.getConfidence(),0.9,delta);
	}

	@Test
	public final void testGetName() {
		assertEquals(gcpl1.getName(),"GCPL1");
	}

}

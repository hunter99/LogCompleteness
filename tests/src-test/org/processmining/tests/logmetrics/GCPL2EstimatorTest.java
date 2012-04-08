package org.processmining.tests.logmetrics;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.processmining.plugins.log.completeness.StatRes;
import org.processmining.plugins.log.completeness.estimators.GCPL2Estimator;

public class GCPL2EstimatorTest {
	GCPL2Estimator gcpl2=new GCPL2Estimator();
	StatRes res=new StatRes(null,0,null);
	double delta=Math.pow(0.1, 10);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetNumOfObservedUnits() {
		assertEquals(gcpl2.getNumOfObservedUnits(res),0);
	}

	@Test
	public final void testCalculate() {
		res.initial4Estimating();
		gcpl2.calculate(0.1, 0.9, 10, 10000, res);
		assertEquals(res.getExpectedCompleteLogLength(),Math.round(100/Math.pow(0.1, 3)));
	}

	@Test
	public final void testEstimate() {
		res.initial4Estimating();
		gcpl2.estimate(res);
		assertEquals(res.getCoverageProbability(),-1.0,delta);
	}

	@Test
	public final void testGetName() {
		assertEquals(gcpl2.getName(),"GCPL2");
	}
	
	@Test
	public final void testSetEpsilon() {
		assertEquals(gcpl2.getEpsilon(),0.1,delta);
	}

	@Test
	public final void testSetConfidence() {
		assertEquals(gcpl2.getConfidence(),0.9,delta);
	}


}

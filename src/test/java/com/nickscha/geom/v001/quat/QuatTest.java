package com.nickscha.geom.v001.quat;

import org.junit.Assert;
import org.junit.Test;

import com.nickscha.geom.quat.Quat;
import com.nickscha.geom.quat.Quatf;


public class QuatTest {
	
	@Test
	public void testQuatf(){
		Quatf result = Quat.of(1, 0, 1, 0);
		Assert.assertEquals(Quatf.of(1, 0, 1, 0), result);
	}

}

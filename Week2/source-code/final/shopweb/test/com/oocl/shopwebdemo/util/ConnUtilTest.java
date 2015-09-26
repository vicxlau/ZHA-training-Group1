package com.oocl.shopwebdemo.util;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConnUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetCloseConnection() {
		ConnUtil.jdbcUtil.closeJdbcObjects(
					null,
					null,
					ConnUtil.jdbcUtil.getConnection()
				);
	}
}

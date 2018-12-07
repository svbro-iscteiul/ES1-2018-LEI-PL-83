package jUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.esi.projeto.form.MainWindow;

/**
 * Test class
 *
 */
public class TestMainWindow {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		MainWindow main = new MainWindow();
		
		main.setEmailOff(true);
		main.setFacebookOff(true);
		main.setTwitterOff(true);
		boolean temp = main.isTwitterOff();
		assertTrue(temp);
		temp = main.isFacebookOff();
		assertTrue(temp);
		temp = main.isEmailOff();
		assertTrue(temp);
		
		main.refreshAllTable();
	}

}

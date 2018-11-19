package jUnitTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.esi.projeto.form.models.GmailAPITest;
import pt.iscte.esi.projeto.form.models.TwitterAPITest;
import pt.iscte.esi.projeto.utils.XMLTester;

/**
 * Test class
 *
 */
public class TestsAPis {

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
		new TwitterAPITest().main(null);
		new GmailAPITest().main(null);
		new XMLTester().main(null);
//		new FacebookMain().main(null);
		
	}

}

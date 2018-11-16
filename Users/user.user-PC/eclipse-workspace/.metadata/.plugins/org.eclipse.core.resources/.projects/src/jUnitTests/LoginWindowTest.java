package jUnitTests;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.esi.projeto.form.LoginWindow;

class LoginWindowTest {

	@Test
	void test() {
		new LoginWindow();

	}

}

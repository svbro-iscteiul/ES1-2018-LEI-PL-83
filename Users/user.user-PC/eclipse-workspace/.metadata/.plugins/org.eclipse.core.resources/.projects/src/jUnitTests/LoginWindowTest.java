package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.junit.jupiter.api.Test;

import pt.iscte.esi.projeto.form.LoginWindow;

class LoginWindowTest {

	@Test
	void test() {
		new LoginWindow();

	}

}

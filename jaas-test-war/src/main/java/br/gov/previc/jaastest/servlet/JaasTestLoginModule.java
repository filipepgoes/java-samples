package br.gov.previc.jaastest.servlet;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class JaasTestLoginModule implements LoginModule{
	private static final Object TEST_PASSWORD = "jaastest";
	private static final Object TEST_USERNAME = "jaastest";
	private CallbackHandler callbackHandler=null;
	private boolean success=false;
	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		return success;
	}

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
		this.callbackHandler=callbackHandler;
		
	}

	@Override
	public boolean login() throws LoginException {
		Callback [] ca = new Callback[2];
		ca[0]=new NameCallback("Username: ");
		ca[1]=new PasswordCallback("Password: ", false);
		try {
			callbackHandler.handle(ca);
		} catch (IOException | UnsupportedCallbackException e) {
			e.printStackTrace();
		}
		String name=((NameCallback)ca[0]).getName();
		String password=new String(((PasswordCallback)ca[1]).getPassword());
		if (TEST_USERNAME.equals(name)&&TEST_PASSWORD.equals(password)) {
			System.out.println("Authentication successfull...");
			success=true;
		}
		else {
			success=false;
		}
		return success;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

}

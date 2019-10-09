package br.gov.previc.jaastest.servlet;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class JaasTestCallbackHandler implements CallbackHandler{
	private String user=null;
	private String password=null;
	public JaasTestCallbackHandler(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	@Override
	public void handle(Callback[] ca) throws IOException, UnsupportedCallbackException {
		int counter=0;
		while(counter<ca.length) {
			if (ca[counter] instanceof NameCallback) {
				NameCallback nameCallback=(NameCallback)ca[counter++];
				nameCallback.setName(user);
			}
			else if (ca[counter] instanceof PasswordCallback) {
				PasswordCallback passwordCallback=(PasswordCallback) ca[counter++];
				passwordCallback.setPassword(password.toCharArray());
			}
		}
		
	}

}

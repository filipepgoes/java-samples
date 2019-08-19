package br.gov.previc.jaastest;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class SampleAcn {

	public static void main(String[] args)  {
		LoginContext lc=null;
		try {
			lc = new LoginContext("Sample",new MyCallbackHandler());
		} catch (LoginException e) {
			System.err.println("Cannot create LoginContext. "+e.getMessage());
			System.exit(-1);
		}
		try {
			lc.login();
		} catch (LoginException e) {
			System.out.println("Sorry");
	        System.exit(-1);
		}
		System.out.println("Authentication succeeded!");
	}

}

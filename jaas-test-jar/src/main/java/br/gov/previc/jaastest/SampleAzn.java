package br.gov.previc.jaastest;

import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Iterator;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class SampleAzn {

	public static void main(String[] args) {
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
		Subject mySubject = lc.getSubject();
		Iterator principalIterator = mySubject.getPrincipals().iterator();
		System.out.println("Authenticated user has the following Principals:");
        while (principalIterator.hasNext()) {
            Principal p = (Principal)principalIterator.next();
            System.out.println("\t" + p.toString());
        }

        System.out.println("User has " +
                        mySubject.getPublicCredentials().size() +
                        " Public Credential(s)");

        // now try to execute the SampleAction as the authenticated Subject
        PrivilegedAction action = new SampleAction();
        Subject.doAsPrivileged(mySubject, action, null);

        System.exit(0);
	}

}

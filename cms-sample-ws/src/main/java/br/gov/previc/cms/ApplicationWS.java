package br.gov.previc.cms;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



@ApplicationPath("rest")
public class ApplicationWS extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	   private Set<Class<?>> empty = new HashSet<Class<?>>();

	   public ApplicationWS()
	   {
	      singletons.add(new PublicWS());
	      singletons.add(new ProtectedByAnnotationWS());
	      singletons.add(new ProtectedBySecurityConstraintWS());
	      singletons.add(new PartiallyProtectedWS());
	   }

	   @Override
	   public Set<Class<?>> getClasses()
	   {
	      return empty;
	   }

	   @Override
	   public Set<Object> getSingletons()
	   {
	      return singletons;
	   }
}

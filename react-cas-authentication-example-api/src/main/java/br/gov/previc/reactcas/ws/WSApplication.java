package br.gov.previc.reactcas.ws;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.gov.previc.reactcas.security.SecurityFilter;

@ApplicationPath("rest")
public class WSApplication extends Application
{
   private Set<Object> singletons = new HashSet<Object>();
   private Set<Class<?>> empty = new HashSet<Class<?>>();

   public WSApplication()
   {
      singletons.add(new WS());
      singletons.add(new SecurityFilter());
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

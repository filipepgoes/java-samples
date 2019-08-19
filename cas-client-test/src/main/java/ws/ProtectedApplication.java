package ws;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest")
public class ProtectedApplication extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	
	public ProtectedApplication()
	{
		singletons.add(new ProtectedWebService());
		//singletons.add(new SecurityFilter());
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

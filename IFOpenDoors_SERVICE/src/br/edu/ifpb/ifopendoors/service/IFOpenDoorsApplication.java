package br.edu.ifpb.ifopendoors.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class IFOpenDoorsApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public IFOpenDoorsApplication() {
		
		// ADD YOUR RESTFUL RESOURCES HERE
		this.singletons.add(new ConsultarIFOpenDoors());
		this.singletons.add(new RestIndexIFOpenDoors());
	}

	public Set<Class<?>> getClasses() {
		return this.empty;
	}

	public Set<Object> getSingletons() {
		return this.singletons;
	}
}
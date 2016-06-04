package br.edu.ifpb.ifopendoors.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.edu.ifpb.ifopendoors.controller.DoorController;
import br.edu.ifpb.ifopendoors.controller.PersonController;
import br.edu.ifpb.ifopendoors.controller.RoomController;
import br.edu.ifpb.ifopendoors.controller.TypeRoomController;

public class IFOpenDoorsApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public IFOpenDoorsApplication() {
		
		// ADD YOUR RESTFUL RESOURCES HERE
		this.singletons.add(new RoomController());
		this.singletons.add(new RestServices());
		this.singletons.add(new PersonController());
		this.singletons.add(new DoorController());
		this.singletons.add(new TypeRoomController());
	}

	public Set<Class<?>> getClasses() {
		return this.empty;
	}

	public Set<Object> getSingletons() {
		return this.singletons;
	}
}
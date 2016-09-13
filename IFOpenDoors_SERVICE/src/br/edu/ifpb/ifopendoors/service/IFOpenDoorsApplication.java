package br.edu.ifpb.ifopendoors.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import br.edu.ifpb.ifopendoors.controller.DoorController;
import br.edu.ifpb.ifopendoors.controller.GateController;
import br.edu.ifpb.ifopendoors.controller.PersonController;
import br.edu.ifpb.ifopendoors.controller.RoleController;
import br.edu.ifpb.ifopendoors.controller.RoomController;
import br.edu.ifpb.ifopendoors.controller.TypeRoomController;

public class IFOpenDoorsApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public IFOpenDoorsApplication() {
		
		CorsFilter filter = new CorsFilter();
		filter.getAllowedOrigins().add("*");
		filter.setAllowedMethods("POST, GET, DELETE, PUT, OPTIONS");
		filter.setAllowedHeaders("Content-Type, Authorization");
		
		this.singletons.add(filter);
		
		// Controllers das entidades.
		this.singletons.add(new RoomController());
		this.singletons.add(new PersonController());
		this.singletons.add(new DoorController());
		this.singletons.add(new TypeRoomController());
		this.singletons.add(new GateController());
		this.singletons.add(new RoleController());
		
		// Mapeamento do index e serviços.
		this.singletons.add(new IFOpenDoorsRestServices());
		this.singletons.add(new IFOpenDoorsIndex());
	}

	public Set<Class<?>> getClasses() {
		return this.empty;
	}

	public Set<Object> getSingletons() {
		return this.singletons;
	}
}
package com.example.rest.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.rest.dao.DocenteModel;
import com.example.rest.entidades.Docente;



@Path("/servicios")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ServicioRest {
	private static final Log log = LogFactory.getLog(ServicioRest.class);
	
	private DocenteModel daoDo = new DocenteModel();

	
	
	@GET
	@Path("/docente")
	public Response listarProveedorTodos() {
		log.info("listarTodos docente rest ");
		return Response.ok(daoDo.listarTodosDocentes()).build();
	}
	@POST
	@Path("/docente")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response registra(Docente obj) {
		log.info("Registra docente " + obj.getIdDocente());
		if (daoDo.insertaDo(obj) > 0)
			return Response.ok().build();
		else
			return Response.notModified().build();
	}

	@PUT
	@Path("/docente")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response atualiza(Docente obj) {
		log.info("Actualiza docente " + obj.getIdDocente());
		if (daoDo.actualizaDo(obj) > 0)
			return Response.ok().build();
		else
			return Response.notModified().build();
	}

	@DELETE
	@Path("/docente/{idDocente}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response elimina(@PathParam("idDocente") int id) {
		log.info("Elimina docente " + id);
		if (daoDo.eliminaDo(id) > 0)
			return Response.ok().build();
		else
			return Response.notModified().build();
	}


	

}
package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/logement")
public class LogementRessources {
    static LogementBusiness help = new LogementBusiness();
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getAll(){
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help.getLogements()).
                build();
    }
    @GET
    @Path("/getByReference/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByReference(@PathParam("reference") int reference) {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogementsByReference(reference))
                .build();
    }

    @GET
    @Path("/getByDelegation/{delegation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDelegation(@PathParam("delegation") String delegation) {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogementsByDeleguation(delegation))
                .build();
    }



    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
      boolean added =  help.addLogement(logement);

      if(added){
          return Response.status(200).build();
      }
      else{
          return Response.status(404).build();
      }
    }

    @DELETE
    @Path("/delete/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean deleted = help.deleteLogement(reference);
        if (deleted) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"Logement supprimé avec succès\"}")
                    .build();
        } else {
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"Logement non trouvé\"}")
                    .build();
        }
    }

    @PUT
    @Path("/update/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = help.updateLogement(reference, logement);
        if (updated) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"Logement maj avec succès\"}")
                    .build();
        } else {
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"Logement non trouvé\"}")
                    .build();
        }
    }
}





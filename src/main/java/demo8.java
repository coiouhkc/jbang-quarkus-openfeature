///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS io.quarkus:quarkus-bom:3.40.1@pom
//DEPS io.quarkus:quarkus-rest
//DEPS io.quarkus:quarkus-rest-jackson
//DEPS io.quarkiverse.openfeature:quarkus-openfeature-flagd:1.0.0.Beta1
//FILES ../resources/application.properties
//JAVA 17
//JAVA_OPTIONS -Djava.util.logging.manager=org.jboss.logmanager.LogManager

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import dev.openfeature.sdk.Client;
import io.quarkus.runtime.Quarkus;

public class demo8 {

	public static void main(String[] args) {
		Quarkus.run(args);
	}

	@Path("/feature")
	@ApplicationScoped
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public static class FeatureController {

		@Inject
		Client client;

		@GET
		public Response getFeatureValue(@QueryParam("featureName") String featureName) {
            Boolean featureValue = client.getBooleanValue(featureName, Boolean.FALSE);
			return Response.ok(featureValue).build();
		}
	}
}

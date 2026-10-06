///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS io.quarkus:quarkus-bom:3.40.1@pom
//DEPS io.quarkus:quarkus-rest
//DEPS io.quarkus:quarkus-rest-jackson
//DEPS io.quarkiverse.openfeature:quarkus-openfeature-flagd:1.0.0.Beta1
//FILES application.properties
//JAVA 17
//JAVA_OPTIONS -Djava.util.logging.manager=org.jboss.logmanager.LogManager

package me.abratuhi.demo.openfeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.ImmutableContext;
import dev.openfeature.sdk.Value;

import io.quarkus.runtime.Quarkus;

public class demo8 {

	public static void main(String[] args) {
		Quarkus.run(args);
	}

	@Path("/feature")
	@ApplicationScoped
	@jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public static class FeatureController {

		@Inject
		Client client;

		@GET
		public Response getFeatureValue(String featureName) {
            Boolean featureValue = client.getBooleanValue(featureName, Boolean.FALSE);
			return Response.ok(featureValue).build();
		}
	}
}

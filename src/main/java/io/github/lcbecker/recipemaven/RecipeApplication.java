package io.github.lcbecker.recipemaven;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.bson.Document;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RecipeApplication extends Application<RecipeConfiguration> {
	
	public static void main(String[] args) throws Exception {
		new RecipeApplication().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<RecipeConfiguration> bootstrap) {
		// Used to serve static files.
	}

	@Override
	public void run(RecipeConfiguration config, Environment env) throws Exception {
		// Enable CORS headers
	    final FilterRegistration.Dynamic cors =
	        env.servlets().addFilter("CORS", CrossOriginFilter.class);

	    // Configure CORS parameters
	    cors.setInitParameter("allowedOrigins", "*");
	    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
	    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

	    // Add URL mapping
	    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		
		MongoClient	mongoClient = new MongoClient("localhost" , 27017);
		MongoDatabase db = mongoClient.getDatabase("recipesDB");
		MongoCollection<Document> recipeColl = db.getCollection("recipeCollection");
		env.jersey().register(new io.github.lcbecker.recipemaven.resources.RecipeResource(recipeColl));
	}
}

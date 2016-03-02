package io.github.lcbecker.recipemaven.resources;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import io.github.lcbecker.recipemaven.beans.Recipe;

@Path("/recipes")
public class RecipeResource { 
	
	private MongoCollection<Document> recipeColl;
	
	public RecipeResource(MongoCollection<Document> recipeColl) {
		this.recipeColl = recipeColl;
	}
	
	/**
	 * Returns a list of all recipes in the database.
	 * @return JSON with all recipes.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRecipesList() {
		MongoCursor<Document> cursor = recipeColl.find().iterator();
		ArrayList<String> recipes = new ArrayList<String>();
		try {
			while(cursor.hasNext()) {
				recipes.add(cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}
		Map<String, Object> root = new LinkedHashMap<String, Object>();
		root.put("recipes", recipes);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(root);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return Response.ok(json, MediaType.APPLICATION_JSON).build();	
	}
	
	/**
	 * Adds a recipe to MongoDB.
	 * @param recipe The recipe to add.
	 * @return OK if added successfully.
	 */
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRecipe(Recipe recipe) {
		ObjectMapper mapper = new ObjectMapper();
		Document newRecipe = null;
		try {
			newRecipe = Document.parse(mapper.writeValueAsString(recipe));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		recipeColl.insertOne(newRecipe);
		return Response.ok().build();
	}
	
	@POST
	@Path("/delete")
	public Response deleteRecipe(@FormParam("name") String recipe) {
		Bson condition = new Document("$eq", recipe);
		Bson filter = new Document("name", condition);
		recipeColl.deleteOne(filter);
		return Response.ok().build();
	}
}

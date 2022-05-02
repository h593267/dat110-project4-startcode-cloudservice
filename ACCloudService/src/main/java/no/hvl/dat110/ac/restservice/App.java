package no.hvl.dat110.ac.restservice;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.post;
import static spark.Spark.delete;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	
	static AccessLog accesslog = null;
	static AccessCode accesscode = null;
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}

		// objects for data stored in the service
		
		accesslog = new AccessLog();
		accesscode  = new AccessCode();
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		// for basic testing purposes
		get("/accessdevice/hello", (req, res) -> {
			
		 	Gson gson = new Gson();
		 	
		 	return gson.toJson("IoT Access Control Device");
		});
		
		// TODO: implement the routes required for the access control service
		// as per the HTTP/REST operations describined in the project description
		
		post("/accessdevice/log", (req, res) -> {
			

		 	Gson gson = new Gson();
		 	accesslog.add(req.body());
		 	
		 	
		 	return gson.toJson(req.body());
		});
		
		
		get("/accessdevice/log", (req, res) -> {
		 	
		 	return accesslog.toJson();
		});
		
		
		get("/accessdevice/log/:id",  (req, res) -> {
			
		 	Gson gson = new Gson();
		 	String id = req.params(":id");
		 	

		 	return gson.toJson(accesslog.get(Integer.parseInt(id)));
		});
		
		
		put("/accessdevice/code/:code",  (req, res) -> {
			
		 	Gson gson = new Gson();
		 	String codeString = req.params(":code");
		 	int x = Integer.parseInt(codeString.charAt(0) + "");
		 	int y = Integer.parseInt(codeString.charAt(1) + "");
		 	int[] code = {x,y};
		 	accesscode.setAccesscode(code);
		 	res.body(gson.toJson(codeString));
		 	

		 	return gson.toJson(codeString);
		});
		
		
		get("/accessdevice/code",  (req, res) -> {
			
		 	Gson gson = new Gson();

		 	return gson.toJson(accesscode.getAccesscode());
		});
		
		
		delete("/accessdevice/log",  (req, res) -> {
			
		 	accesslog.clear();

		 	return accesslog.toJson();
		});
		
    }
    
}

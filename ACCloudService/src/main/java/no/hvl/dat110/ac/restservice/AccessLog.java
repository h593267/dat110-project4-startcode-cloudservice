package no.hvl.dat110.ac.restservice;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

public class AccessLog {
	
	// atomic integer used to obtain identifiers for each access entry
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;
	
	public AccessLog () {
		this.log = new ConcurrentHashMap<Integer,AccessEntry>();
		cid = new AtomicInteger(0);
	}

	// TODO: add an access entry to the log for the provided message and return assigned id
	public int add(String message) {
		
		int id = 0;
		AccessEntry entry = new AccessEntry(cid.get(), message); 
		id = cid.get();
		log.put(id, entry);
		
		return id;
	}
		
	// TODO: retrieve a specific access entry from the log
	public AccessEntry get(int id) {
		AccessEntry entry = log.get(id);
		return entry;
		
	}
	
	// TODO: clear the access entry log
	public void clear() {
		log.clear();
	}
	
	// TODO: return JSON representation of the access log
	public String toJson () {
    	
		String json = null;
		Iterator<Integer> it = log.keySet().iterator();
    	while(it.hasNext()) {
    		int id = it.next();
    		AccessEntry entry = get(id);
    		json ="message : " +  entry.getMessage() + " id : " + entry.getId();
    	}
    	return json;
    }
}
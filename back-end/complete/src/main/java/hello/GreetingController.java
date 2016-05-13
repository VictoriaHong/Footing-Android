package hello;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	private static HashMap<String, User> database = new HashMap<String, User>();
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method=RequestMethod.GET, value="/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    @RequestMapping(method=RequestMethod.POST, value="/add")
    public String p_greeting(@RequestBody String postData) {
    	JSONParser parser = new JSONParser();
    	String[] ele = new String[6];
    	try {
			JSONObject jsonObject = (JSONObject) parser.parse(postData);
			ele[0] = (String) jsonObject.get("email");
			ele[1] = (String) jsonObject.get("username");
			ele[2] = (String) jsonObject.get("country");
			ele[3] = jsonObject.get("miles").toString();
			ele[4] = (String) jsonObject.get("journal").toString();
			ele[5] = (String) jsonObject.get("medal").toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(database.containsKey(ele[0])){
			updateUser(ele);
			print();
			return "updated";
		}
		else{
			addUser(ele);
			print();
			return "added";
		}
    }
    
    private void updateUser(String[] ele) {
		database.remove(ele[0]);
		addUser(ele);
	}

	private void addUser(String[] ele) {
		User newUser = new User(ele[0], ele[1], ele[2], ele[3], ele[4], ele[5]);
		database.put(ele[0], newUser);		
	}
	
	private void print(){
		for(String key:database.keySet()){
			database.get(key).userPrint();
		}		
	}   
}

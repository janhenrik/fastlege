package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;
import play.mvc.Scope;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Person extends Model {

	public String navn;
	public String epost;
	@OneToMany
	public List<FastlegeOnske> fastlegeOnsker;

	public static void facebookOAuthCallback(JsonObject data) {
		String epost = data.get("email").getAsString();
		Person person = find("epost=?", epost).first();
		if (person == null) {
			person = new Person();
			person.epost = epost;
			person.save();
		}
		Scope.Session.current().put("user", person.epost);
	}
}

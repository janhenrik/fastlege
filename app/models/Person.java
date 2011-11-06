package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.db.jpa.Model;
import play.mvc.Scope;

import com.google.gson.JsonObject;

@Entity
@Table(name = "Person", uniqueConstraints = @UniqueConstraint(columnNames = { "epost" }))
public class Person extends Model {

	public String navn;
	public String epost;
	@OneToMany
	public List<FastlegeOnske> fastlegeOnsker;

	public static void facebookOAuthCallback(JsonObject data) {
		String epost = data.get("email").getAsString();
		String navn = data.get("name").getAsString();
		Person person = find("epost=?", epost).first();
		if (person == null) {
			person = new Person();
			person.epost = epost;
			person.navn = navn;
			person.save();
		}
		Scope.Session.current().put("user", person.epost);
	}
}

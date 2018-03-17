package hibernate.demo13.domain;

/**
 * Éí·İÖ¤
 * @author Administrator
 *
 */
public class IdCard {
	private Integer id;
	private String number;
	private Person person;
	
	public Person getPerson(){
		return person;
	}
	public void setPerson(Person person){
		this.person = person;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "IdCard [id=" + id + ", number=" + number + "]";
	}
	
}

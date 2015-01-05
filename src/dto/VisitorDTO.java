package dto;

public class VisitorDTO {
	private int id;
	private String name;
	private String prename;
	private String phone;
	
	public VisitorDTO(int id, String name, String prename, String phone) {
		this.id = id;
		this.name = name;
		this.prename = prename;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
}

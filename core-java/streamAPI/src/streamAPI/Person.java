package streamAPI;

public class Person {
	
	private String name;
	private int age;
	private String mobileNo;
	private String state;
	private String country;
	
	public Person() {
		super();
	}

	public Person(String name, int age, String mobileNo, String state, String country) {
		super();
		this.name = name;
		this.age = age;
		this.mobileNo = mobileNo;
		this.state = state;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", mobileNo=" + mobileNo + ", state=" + state + ", country="
				+ country + "]";
	}
	
	
	
	
	

}

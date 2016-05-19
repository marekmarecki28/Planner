package com.planner.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import com.planner.enums.Sex;

@Entity
@Table( name = "USER" )
public class User {

	private static final long serialVersionUID = 2760109101582898698L;

	public User(){
	}
	
	public User(Long userId, String firstname, String lastname, Sex sex, int age, String username, String password, String salt, String email){
		this.userId = userId;
		this.firstName = firstname;
		this.lastName = lastname;
		this.sex = sex;
		this.age = age;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.email = email;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name="USER_ID")
	private Long userId;

	@Validate("required")
	@Column
	private String firstName;
	
	@Column
	private String lastName;

	@Column
	private int age;

	@Column
	private Sex sex;
	
	@Validate("required")
	@Column
    private String email;
	
	@Column
	private String username;
   
	@Validate("required")
    @Column
    private String password;
   
    @Column
    private String salt;
    
    public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

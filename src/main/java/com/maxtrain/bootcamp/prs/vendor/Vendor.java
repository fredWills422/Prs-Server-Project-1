package com.maxtrain.bootcamp.prs.vendor;

import javax.persistence.*;


@Entity
@Table(uniqueConstraints=@UniqueConstraint(name = "UIDX_code", columnNames = {"code"}))
public class Vendor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=10, nullable=false)
	private String code;
	@Column(length=255, nullable=false)
	private String name;
	@Column(length=255, nullable=false)
	private String address;
	@Column(length=255, nullable=false)
	private String city;
	@Column(length=2, nullable=false)
	private String State;
	@Column(length=5, nullable=false)
	private int zip;
	@Column(length=12, nullable=false)
	private String phoneNumber;
	@Column(length=100, nullable=false)
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Vendor(){}
}

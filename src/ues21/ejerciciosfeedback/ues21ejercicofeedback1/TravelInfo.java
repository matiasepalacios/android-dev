package ues21.ejerciciosfeedback.ues21ejercicofeedback1;

import java.util.ArrayList;


public class TravelInfo {


	protected ArrayList<TravelInfo> array;



	protected String city;
	protected String country;
	protected int year;
	protected String comments;

	public TravelInfo(String city, String country, int year, String comments) {
		super();
		this.city = city;
		this.country = country;
		this.year = year;
		this.comments = comments;
	}

	public TravelInfo(String city, String country, int year) {
		super();
		this.city = city;
		this.country = country;
		this.year = year;
		this.comments = null;
	}


	public void putInArray() {

	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getYear() {
		return (int) year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}



}


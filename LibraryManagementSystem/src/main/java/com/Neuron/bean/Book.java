package com.Neuron.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookId;
	private String Title;
	private String Author;
	private boolean isIssued;
	private Integer borrowingAmount;
	
	@JsonIgnore
	private LocalDate issuedDate;
	
	@JsonIgnore
	private LocalDate returnDate;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "borrowedBooks")
	private List<User> users=new ArrayList<>();
	

}

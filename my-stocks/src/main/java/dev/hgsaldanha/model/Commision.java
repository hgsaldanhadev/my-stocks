package dev.hgsaldanha.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Commision {
	
	private Double perTrade;
	
	private Double perExercise;

}

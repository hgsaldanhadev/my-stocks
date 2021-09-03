package dev.hgsaldanha.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Commision {
	
	private Long perTrade;
	
	private Long perExercise;

}

package dev.hgsaldanha.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SWING")
public class SwingTradeOperation extends Operation {

}

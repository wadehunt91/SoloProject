package com.wadehunt.mystorytime.models;

public class Joke {
	String setup;
	String delivery;
	public Joke() {}
	public String getSetup() {
		return setup;
	}
	public void setSetup(String setup) {
		this.setup = setup;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	@Override
	public String toString() {
		return "Joke [setup=" + setup + ", delivery=" + delivery + "]";
	};
	
	
}

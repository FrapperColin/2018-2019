package main;

import state.Context;

public class Controller {
	public Context context ;
       
	public Controller() {
		this.context = new Context();
	}

	public void movingForward () {
		this.context.movingForward();
	}
	
	public void movingBackward() {
		this.context.movingBackward();
	}
	
	public void stopping() {
		this.context.stoppingCar();
	}
	
	public void turnLeft(int ratio) {
		this.context.turnLeft(ratio);
	}
	
	public void turnRight(int ratio) {    
		this.context.turnRight(ratio);
	}

	public void accelerate(int value) {
		this.context.accelerate(value);
	}

	public void decelerated(int value) {
		this.context.decelerated(value);
	}

	public void slowDown() {
		this.context.slowDown();
	}

	public void contact() throws InterruptedException {
		this.context.contact();
	}

	public Context getContext() {
		return this.context;
	}
}
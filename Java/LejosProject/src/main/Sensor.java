package main;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import state.Context;

public class Sensor extends Thread{

	private EV3UltrasonicSensor capteur ;  
	private Context context;
	private boolean running;
	private final SampleProvider distanceMode;
	private final float[] distanceSample;

	public Sensor(EV3UltrasonicSensor _capteur) {
		this.capteur = _capteur; 
		this.running = true ;
		this.capteur.enable();
		this.distanceMode = this.capteur.getDistanceMode();
		this.distanceSample = new float[this.distanceMode.sampleSize()];
	}
	
	public boolean getRunning() {
		return this.running;
	}
	
	@Override
	public void run() {
		try {
			this.contact();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void contact() throws InterruptedException {
		while (this.running) {
			int distance = getDistance();
			if(5 < distance && distance <10 ) {
				this.context.slowDown();
			} else if(distance <= 5) {
				this.context.contact();
			}
		}
	}
	
	public boolean isUltrasonicDetected() {
		int distance = getDistance();
		if(distance<30) {
			return true ;
		}
		return false ;

	}
	
	public int getDistance() {
		this.distanceMode.fetchSample(this.distanceSample, 0);
		float result = this.distanceSample[0];
		return (int) (result * 100);
	}


	public Context getContext() {
		return this.context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public void arret() {
		this.capteur.disable();
		this.running = false ;
	}
}

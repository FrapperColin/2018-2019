package state;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import main.Motor;
import main.Sensor;

public class Context {
   private StateCar state;
   private Motor leftMotor; 
   private Motor rightMotor;
   private Sensor ultrasonicSensor;


   public Context() {
      this.setState(new StateCarStopped());
      this.leftMotor = new Motor(new EV3LargeRegulatedMotor(MotorPort.D));
	  this.rightMotor = new Motor(new EV3LargeRegulatedMotor(MotorPort.A));
	  RegulatedMotor T[] = {this.rightMotor.getMotor()};
  	  this.leftMotor.getMotor().synchronizeWith(T);
  	  this.ultrasonicSensor = new Sensor(new EV3UltrasonicSensor(SensorPort.S2));
  	  this.ultrasonicSensor.setContext(this);
  	  this.ultrasonicSensor.start();
   }
   
   public void setState(StateCar state) {
      this.state = state;		
   }

   public StateCar getState() {
      return state;
   }

   public Motor getLeftMotor() {
	   return leftMotor;
   }
	
   public void setLeftMotor(Motor leftMotor) {
	   this.leftMotor = leftMotor;
   }
	
   public Motor getRightMotor() {
		return rightMotor;
   }

   public void setRightMotor(Motor rightMotor) {
	   this.rightMotor = rightMotor;
   }
   
   public Sensor getUltrasonicSensor() {
	   return this.ultrasonicSensor;
   }
   
   public void setUltrasonicSensor(Sensor ultrasonicSensor) {
	   this.ultrasonicSensor = ultrasonicSensor;
   }
   
   public void turnLeft(int ratio) {
	   this.state.turnLeft(this, ratio);
   }
   
   public void turnRight(int ratio) {
	   this.state.turnRight(this, ratio);
   }

   public void decelerated(int value) {
	   if(this.leftMotor.getSpeed() - value <= 0 || this.rightMotor.getSpeed() - value <= 0) {
			this.leftMotor.getMotor().startSynchronization();
	    	this.leftMotor.stop();
	    	this.rightMotor.stop();
	        this.setState(new StateCarStopped());
	        this.leftMotor.getMotor().endSynchronization();
		} else {
			this.leftMotor.getMotor().startSynchronization();
	    	this.leftMotor.setSpeed(leftMotor.getSpeed() - value);
	        this.rightMotor.setSpeed(rightMotor.getSpeed() - value);
	        this.leftMotor.getMotor().endSynchronization();
		}
   }

   public void accelerate(int value) {
	   if (this.leftMotor.getSpeed() < 250 && this.rightMotor.getSpeed() < 250 ) {
		   this.leftMotor.getMotor().startSynchronization();
	   	   this.leftMotor.setSpeed(leftMotor.getSpeed() + value);
	       this.rightMotor.setSpeed(rightMotor.getSpeed() + value);
	       this.leftMotor.getMotor().endSynchronization();
	   }
   }

   public void contact() throws InterruptedException {
	   if(this.getState() instanceof StateCarSlowingDown) {
			this.setState(new StateCarBackUp());
			this.leftMotor.getMotor().startSynchronization();
			this.leftMotor.stop();
			this.rightMotor.stop();
	        this.leftMotor.getMotor().endSynchronization();
	        while(ultrasonicSensor.isUltrasonicDetected()) {
				this.leftMotor.getMotor().startSynchronization();
		        this.leftMotor.movingForward();
		        this.rightMotor.movingBackward();
		        this.leftMotor.getMotor().endSynchronization();
	        }
	        Thread.sleep(4000);
	        this.leftMotor.getMotor().startSynchronization();
	        this.leftMotor.movingForward();
	        this.leftMotor.setSpeed(leftMotor.getPreviousSpeed());
	        this.rightMotor.movingForward();
	        this.rightMotor.setSpeed(rightMotor.getPreviousSpeed());
	        this.leftMotor.getMotor().endSynchronization();
	        this.setState(new StateCarMovingForward());
		}
   }
   
   public void slowDown() {
	   this.state.slowDown(this);
   }
   
   public void movingForward() {
	   this.state.movingForward(this);
   }

   public void movingBackward() {
	   this.state.movingBackward(this);
   }

   public void stoppingCar() {
	   this.state.stoppingCar(this);
   }
}

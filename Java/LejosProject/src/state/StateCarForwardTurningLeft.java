package state;

public class StateCarForwardTurningLeft implements StateCar {
	
	@Override
	public void initialize(Context context) {
		context.setState(this);	
	}

	@Override
	public void movingForward(Context context) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().setSpeed(context.getLeftMotor().getSpeed());
		context.getRightMotor().setSpeed(context.getLeftMotor().getSpeed());
		context.getLeftMotor().getMotor().endSynchronization();
        context.setState(new StateCarMovingForward());	
	}

	@Override
	public void movingBackward(Context context) {
		System.out.println("Can't moving backward if you're moving forward, better stop first");
	}

	@Override
	public void stoppingCar(Context context) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().stop();
		context.getRightMotor().stop();
        context.getLeftMotor().getMotor().endSynchronization();    
        context.setState(new StateCarStopped());
	}

	@Override
	public void slowDown(Context context) {	
		context.getLeftMotor().setPreviousSpeed(context.getLeftMotor().getSpeed());
		context.getRightMotor().setPreviousSpeed(context.getRightMotor().getSpeed());
		context.setState(new StateCarSlowingDown());
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().setSpeed(30);
    	context.getRightMotor().setSpeed(30);
    	context.getLeftMotor().getMotor().endSynchronization();	
	}

	@Override
	public void turnLeft(Context context, int ratio) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().setSpeed(context.getLeftMotor().getSpeed());
		context.getRightMotor().setSpeed(context.getRightMotor().getSpeed() * ratio);
		context.getLeftMotor().getMotor().endSynchronization();		
	}

	@Override
	public void turnRight(Context context, int ratio) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().setSpeed(context.getLeftMotor().getSpeed());
		context.getRightMotor().setSpeed(context.getRightMotor().getSpeed() / ratio);
		context.getLeftMotor().getMotor().endSynchronization();
		context.setState(new StateCarMovingForward());
	}
}

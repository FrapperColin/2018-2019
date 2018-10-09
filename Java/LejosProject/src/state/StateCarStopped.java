package state;


public class StateCarStopped implements StateCar {
	@Override
	public void initialize(Context context) {
		context.setState(this);	
	}

	@Override
	public void movingForward(Context context) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().movingForward();
		context.getRightMotor().movingForward();
		context.getLeftMotor().getMotor().endSynchronization();
        context.setState(new StateCarMovingForward());	
	}

	@Override
	public void movingBackward(Context context) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().movingBackward();
		context.getRightMotor().movingBackward();
        context.getLeftMotor().getMotor().endSynchronization();
        context.setState(new StateCarMovingBackward());	
	}

	@Override
	public void stoppingCar(Context context) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().stop();
		context.getRightMotor().stop();
        context.getLeftMotor().getMotor().endSynchronization();
	}

	@Override
	public void slowDown(Context context) {	
	}

	@Override
	public void turnLeft(Context context, int ratio) {
	}

	@Override
	public void turnRight(Context context, int ratio) {
	}

}

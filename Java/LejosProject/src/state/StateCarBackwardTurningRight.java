package state;

public class StateCarBackwardTurningRight implements StateCar {
	
	@Override
	public void initialize(Context context) {
		context.setState(this);	
	}

	@Override
	public void movingForward(Context context) {
		System.out.println("You can't moving forward if you're moving backward, better stop first");
	}

	@Override
	public void movingBackward(Context context) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().setSpeed(context.getRightMotor().getSpeed());
		context.getRightMotor().setSpeed(context.getRightMotor().getSpeed());
        context.getLeftMotor().getMotor().endSynchronization();
        context.setState(new StateCarMovingBackward());	
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
	}

	@Override
	public void turnLeft(Context context, int ratio) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().setSpeed(context.getLeftMotor().getSpeed() / ratio);
		context.getRightMotor().setSpeed(context.getRightMotor().getSpeed());
		context.getLeftMotor().getMotor().endSynchronization();
		context.setState(new StateCarMovingBackward());
	}

	@Override
	public void turnRight(Context context, int ratio) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().setSpeed(context.getLeftMotor().getSpeed() * ratio);
		context.getRightMotor().setSpeed(context.getRightMotor().getSpeed());
		context.getLeftMotor().getMotor().endSynchronization();
	}
}
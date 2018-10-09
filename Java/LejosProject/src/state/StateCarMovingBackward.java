package state;

public class StateCarMovingBackward implements StateCar {

	@Override
	public void initialize(Context context) {
		System.out.println("Player is in car moving backward state");
	    context.setState(this);	
	}

	@Override
	public void movingForward(Context context) {
		System.out.println("You can't moving forward if you're moving backward, better stop first");
	}

	@Override
	public void movingBackward(Context context) {
		System.out.println("Already moving backward");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnLeft(Context context, int ratio) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().setSpeed(context.getLeftMotor().getSpeed());
		context.getRightMotor().setSpeed(context.getRightMotor().getSpeed() * ratio);
		context.getLeftMotor().getMotor().endSynchronization();	
		context.setState(new StateCarBackwardTurningLeft());
	}

	@Override
	public void turnRight(Context context, int ratio) {
		context.getLeftMotor().getMotor().startSynchronization();
		context.getLeftMotor().setSpeed(context.getLeftMotor().getSpeed() * ratio);
		context.getRightMotor().setSpeed(context.getRightMotor().getSpeed());
		context.getLeftMotor().getMotor().endSynchronization();
		context.setState(new StateCarBackwardTurningRight());
	}

}

package state;

public class StateCarSlowingDown implements StateCar{
	@Override
	public void initialize(Context context) {
	    context.setState(this);	
	}

	@Override
	public void movingForward(Context context) {
		System.out.println("Can't moving forward while slowing down");
		
	}

	@Override
	public void movingBackward(Context context) {
		System.out.println("Can't moving backward while slowing down, please first stop the car");
		
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
	}

	@Override
	public void turnRight(Context context, int ratio) {
	}
}

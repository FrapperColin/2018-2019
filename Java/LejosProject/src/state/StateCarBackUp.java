package state;

public class StateCarBackUp implements StateCar{

	@Override
	public void initialize(Context context) {
		System.out.println("Player is in car backUp state");
	    context.setState(this);	
	}

	@Override
	public void movingForward(Context context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void movingBackward(Context context) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}

	@Override
	public void turnRight(Context context, int ratio) {
		// TODO Auto-generated method stub	
	}
	
	
	
}

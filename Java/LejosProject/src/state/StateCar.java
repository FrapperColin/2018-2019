package state;

public interface StateCar {
	public void initialize(Context context);
	public void movingForward(Context context);
	public void movingBackward(Context context);
	public void stoppingCar(Context context);
	public void slowDown(Context context);
	public void turnLeft(Context context, int ratio);
	public void turnRight(Context context, int ratio);
}

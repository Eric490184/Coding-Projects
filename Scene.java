package animatedscene;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import assignment9.Drawable;
import edu.princeton.cs.introcs.StdDraw;
import support.cse131.NotYetImplementedException;
import support.cse131.Timing;

public class Scene implements Drawable {
	private final Color backgroundColor;
	private final List<Drawable> drawables;
	private final List<Cloud> clouds;
	public FilledCircle moon, cutMoon;
	public House house;
	
	public Scene(Color backgroundColor, int numClouds) {
		this.backgroundColor = backgroundColor;
		drawables = new LinkedList<>();
		clouds = RandomUtils.createRandomCloudLocations(numClouds);
		moon = new FilledCircle(.8,.35,.1,Color.WHITE);
		cutMoon = new FilledCircle(.76,.35,.08,this.backgroundColor);
		house = new House (0.2, 0, 0.15);
		// TODO: add any Drawable objects you want to draw before the clouds here.
		// note: celestial objects will hopefully be further away than the clouds.
		
		drawables.add(moon);
		drawables.add(cutMoon);
		drawables.addAll(clouds);
		
		
		// TODO: add any Drawable objects you want to draw after the clouds here
		
		drawables.add(house);
	}

	/**
	 * @return the drawables
	 */
	public List<Drawable> getDrawables() {

		return drawables;

	}

	@Override
	public void draw() {
		StdDraw.clear(backgroundColor);
		for (Drawable drawable : getDrawables()) {

			drawable.draw();

		}
	}

	/**
	 * @return the clouds
	 */
	public List<Cloud> getClouds() {

		return clouds;

	}

	private void advanceClouds(double deltaTime) {
		for (Cloud cloud : getClouds()) {
			//StdDraw.enableDoubleBuffering();
			cloud.advance(deltaTime);
			
			
		}
	}

	public void advanceAll(double deltaTime) {
		advanceClouds(deltaTime);
	}

	public static void setupDrawing() {
		StdDraw.setCanvasSize(1024, 512);
		StdDraw.setXscale(0, 1.0);
		StdDraw.setYscale(0, 0.5);
	}

	public static void main(String[] args) {
		setupDrawing();
		StdDraw.enableDoubleBuffering();
		int numClouds = 20; // feel free to change, if desired
		Color skyColor = new Color(78, 51, 146); // feel free to change, if desired
		Scene scene = new Scene(skyColor, numClouds);
		double prevTime = Timing.getCurrentTimeInSeconds();
		while (!StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			double currTime = Timing.getCurrentTimeInSeconds();
			double deltaTime = currTime - prevTime;
			if (deltaTime > 0.0) {
				scene.advanceAll(deltaTime);
				scene.draw();
				StdDraw.show();
			}
			StdDraw.pause(10);
			prevTime = currTime;
		}
	}
}

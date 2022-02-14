package assignment8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.ArgsProcessor;
import support.cse131.NotYetImplementedException;
import support.cse131.Timing;
import zombies.ZombieSimulationFiles;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class ZombieSimulator {
	private static final String ZOMBIE_TOKEN_VALUE = "Zombie";
	private List<Entity> entities;

	

	/**
	 * Constructs a ZombieSimulator with an empty list of Entities.
	 */
	public ZombieSimulator() {
		
			entities = new LinkedList<Entity>();
		
	}

	/**
	 * @return the current list of active entities (that is: those which have not
	 *         been consumed).
	 */
	public List<Entity> getEntities() {
		
			return entities;
		
	}

	/**
	 * Reads an entire zombie simulation file from a specified ArgsProcessor, adding
	 * each Entity to the list of entities.
	 * 
	 * @param ap ArgsProcessor to read the complete zombie simulation file format.
	 */
	public void readEntities(ArgsProcessor ap) {
		
		int n = ap.nextInt();
		for (int i=0; i<n; ++i) {
			String what = ap.nextString();
			boolean iz = ZOMBIE_TOKEN_VALUE.equals(what);
			Entity ent = new Entity(iz, ap.nextDouble(), ap.nextDouble());
			entities.add(ent);
		}
	}

	/**
	 * @return the number of zombies in entities.
	 */
	public int getZombieCount() {
		int izZom=0;
			for(int i=0; i<entities.size();++i) {
				if(entities.get(i).isZombie==true) {
					izZom=izZom+1;
				}
			}
		return izZom;
	}

	/**
	 * @return the number of nonzombies in entities.
	 */
	public int getNonzombieCount() {
		
		int izNonZom=0;
		for(int i=0; i<entities.size();++i) {
			if(entities.get(i).isZombie==false) {
				izNonZom=izNonZom+1;
			}
		}
	return izNonZom;
		
	}

	/**
	 * Draws a frame of the simulation.
	 */
	public void draw() {
		StdDraw.clear();

		// NOTE: feel free to edit this code to support additional features
		for (Entity entity : getEntities()) {
			entity.draw();
		}

		StdDraw.show(); // commit deferred drawing as a result of enabling double buffering
	}

	/**
	 * Updates the entities for the current frame of the simulation given the amount
	 * of time passed since the previous frame.
	 * 
	 * Note: some entities may be consumed and will not remain for future frames of
	 * the simulation.
	 * 
	 * @param deltaTime the amount of time since the previous frame of the
	 *                  simulation.
	 */
	public void update(double deltaTime) {
		LinkedList<Entity> updateEntities = new LinkedList<Entity>();
			for (int i=0; i<entities.size();++i) {
				if(entities.get(i).update(entities, deltaTime)==false) {
					
				}
				else {
					updateEntities.add(entities.get(i));
				}
			}
		entities=updateEntities;
	}

	/**
	 * Runs the zombie simulation.
	 * 
	 * @param args arguments from the command line
	 */
	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering(); // reduce unpleasant drawing artifacts, speed things up

		ArgsProcessor ap = ZombieSimulationFiles.createArgsProcessorFromFile(args);
		ZombieSimulator zombieSimulator = new ZombieSimulator();
		zombieSimulator.readEntities(ap);
		zombieSimulator.draw();
		StdDraw.pause(500);

		double prevTime = Timing.getCurrentTimeInSeconds();
		while (zombieSimulator.getNonzombieCount() > 0) {
			double currTime = Timing.getCurrentTimeInSeconds();
			double deltaTime = currTime - prevTime;
			if (deltaTime > 0.0) {
				zombieSimulator.update(deltaTime);
				zombieSimulator.draw();
			}
			StdDraw.pause(10);
			prevTime = currTime;
		}
	}
}

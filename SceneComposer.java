package assignment9;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import assignment9.scenes.Bubbles;
import assignment9.scenes.Clear;
import assignment9.scenes.Forest;
import assignment9.scenes.Leaves;
import assignment9.scenes.Sequence;
import assignment9.scenes.ifs.Dragon;
import edu.princeton.cs.introcs.StdDraw;
import support.cse131.ArgsProcessor;

public class SceneComposer {

	public static void main(String[] args) {

		Map<String, Drawable> scene = new HashMap<String, Drawable>();
		LinkedList<Drawable> init = new LinkedList<>();
		LinkedList<Drawable> bubble1 = new LinkedList<>();
		LinkedList<Drawable> bubble2 = new LinkedList<>();
		LinkedList<Drawable> bubble3 = new LinkedList<>();
		LinkedList<Drawable> forest1 = new LinkedList<>();
		LinkedList<Drawable> forest2 = new LinkedList<>();
		LinkedList<Drawable> forest3 = new LinkedList<>();
		LinkedList<Drawable> drag1 = new LinkedList<>();
		LinkedList<Drawable> drag2 = new LinkedList<>();
		LinkedList<Drawable> drag3 = new LinkedList<>();
		LinkedList<Drawable> leaf1 = new LinkedList<>();
		LinkedList<Drawable> leaf2 = new LinkedList<>();
		LinkedList<Drawable> leaf3 = new LinkedList<>();
		ArgsProcessor ap = new ArgsProcessor(args);
		StdDraw.enableDoubleBuffering();

		//initial scene
		StdDraw.show();
		init.add(new Forest (20));     
		init.add(new Bubbles(20));
		init.add(new Dragon(0,0,1));
		Sequence s = new Sequence(init);
		s.draw();
		StdDraw.show();
		scene.put("init", s);
		//

		//stored bubble command 1
		bubble1.add(new Bubbles(15));
		Sequence b1 = new Sequence (bubble1);
		scene.put("b1", b1);
		//

		//Stored bubble command 2
		bubble2.add(new Bubbles(35));
		Sequence b2 = new Sequence (bubble2);
		scene.put("b2", b2);
		//

		//Stored bubble command 2
		bubble3.add(new Bubbles(50));
		Sequence b3 = new Sequence (bubble3);
		scene.put("b3", b3);
		//

		//Stored forest command 1
		forest1.add(new Forest(15));
		Sequence f1 = new Sequence (forest1);
		scene.put("f1", f1);
		//

		//Stored forest command 2
		forest2.add(new Forest(35));
		Sequence f2 = new Sequence (forest2);
		scene.put("f2", f2);
		//

		//Stored forest command 3
		forest3.add(new Forest(50));
		Sequence f3 = new Sequence (forest3);
		scene.put("f3", f3);
		//

		//Stored dragon command 1
		drag1.add(new Dragon (.2,.2,.25));
		Sequence d1 = new Sequence (drag1);
		scene.put("d1", d1);
		//

		//Stored dragon command 2
		drag2.add(new Dragon (.4,.4,.5));
		Sequence d2 = new Sequence (drag2);
		scene.put("d2", d2);
		//

		//Stored dragon command 3
		drag3.add(new Dragon (.2,0,.75));
		Sequence d3 = new Sequence (drag3);
		scene.put("d3", d3);
		//
		
		//Stored leaf 1 command
		leaf1.add(new Leaves(25));
		Sequence l1 = new Sequence (leaf1);
		scene.put("l1", l1);
		//
		
		//Stored leaf 2 command
		leaf2.add(new Leaves(50));
		Sequence l2 = new Sequence (leaf2);
		scene.put("l2", l2);
		//
		
		//Stored leaf 3 command
		leaf3.add(new Leaves(75));
		Sequence l3 = new Sequence (leaf3);
		scene.put("l3", l3);
		//
		
		
		
		

		System.out.println("Option of Commands");
		System.out.println("------------------");
		System.out.println("b1 = 15 Bubbles");
		System.out.println("b2 = 35 Bubbles");
		System.out.println("b3 = 50 Bubbles");
		System.out.println("f1 = Forest Scene 1");
		System.out.println("f2 = Forest Scene 2");
		System.out.println("f3 = Forest Scene 3");
		System.out.println("d1 = Dragon 1");
		System.out.println("d2 = Dragon 2");
		System.out.println("d3 = Dragon 3");
		System.out.println("l1 = Leaf 1");
		System.out.println("l2 = Leaf 2");
		System.out.println("l3 = Leaf 3");


		String g = ap.nextString("Let's begin :)");

		while (g.equals("end")==false) {
			if (g.equals("clear")==true) {
				Clear c = new Clear();
				StdDraw.show();
				c.draw();
				StdDraw.show();
			}
			if (scene.containsKey(g)==true) {
				StdDraw.show();
				scene.get(g).draw();
				StdDraw.show();
			}
			if (g.equals("init")) {
				StdDraw.show();
				s.draw();
				StdDraw.show();
			}
			if (g.equals("record")) {
				Clear w = new Clear ();
				StdDraw.show();
				w.draw();
				StdDraw.show();
				String r = ap.nextString("Name of Recording?");
				LinkedList<Drawable> boo =    new LinkedList<>();
				String e = ap.nextString("What are you recording first?");
				while (e.equals("stop")==false) {

					if (scene.containsKey(e)==true) {
						StdDraw.show();
						scene.get(e).draw();
						StdDraw.show();
						boo.add(scene.get(e));
					}
					e = ap.nextString("What next?");
				}
				Sequence l = new Sequence (boo);
				scene.put(r, l);
				System.out.println(r);
			}
			g = ap.nextString("What's next?");
		}

	}

}
    
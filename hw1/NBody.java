import static java.lang.Math.sqrt;

public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double time = 0.0;

		In in = new In(filename);
		int n = in.readInt();
		double r = in.readDouble(); 

		int i;

		/** Read in all the planets in an array */
		Planet[] plist = new Planet[n];
		for(i = 0; i < n; i++) {
			plist[i] = getPlanet(in);
		}

		in.close();

		/** Let's have some music. Not working, damn it. */
		/*StdAudio.play("./audio/nirvana_in_fire.mp3");

		/** Draw the initial universe */
		StdDraw.setXscale(-r, r);
		StdDraw.setYscale(-r, r); 
		String bg = "/images/starfield.jpg";
		StdDraw.picture(0, 0, bg);
		for(i = 0; i < n; i++) {
			plist[i].drawIt();
		}

		/** Creating an animation */
		while(time < T) {
			/* 1. Set net force
			 * 2. Update planet state */
			for(i = 0; i < n; i++) {
				plist[i].setNetForce(plist);
				plist[i].update(dt);
			}

			/* Re-draw the universe */
			StdDraw.picture(0, 0, bg);
			StdDraw.setPenColor(StdDraw.WHITE);
			for(i = 0; i < n; i++) {
				plist[i].drawIt();
			}
			StdDraw.show(10);
			time += dt;
		}
		/*StdAudio.close();*/

		System.exit(0);
	}

	public static Planet getPlanet(In in0) {
		double xPos = in0.readDouble();
		double yPos = in0.readDouble();
		double xVel = in0.readDouble();
		double yVel = in0.readDouble();
		double mass = in0.readDouble();
		String img = in0.readString();

		return (new Planet(xPos, yPos, xVel, yVel, mass, img));
	}
}
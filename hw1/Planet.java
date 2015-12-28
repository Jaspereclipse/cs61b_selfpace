import static java.lang.Math.sqrt;

public class Planet {
	
	public double x;
	public double y;
	public double xVelocity;
	public double yVelocity;
	public double xAccel = 0.0;
	public double yAccel = 0.0;
	public double mass;
	public String img;
	public double xNetForce = 0.0;
	public double yNetForce = 0.0;

	/** Constructor */
	public Planet(double px, double py, double vx, double vy, double m, String img0) {
		x = px;
		y = py;
		xVelocity = vx;
		yVelocity = vy;
		mass = m;
		img = img0;
	}

	/** Calculate distance between two planet */
	public double calcDistance(Planet p) {
		return sqrt((this.x - p.x)*(this.x - p.x) + (this.y - p.y)*(this.y - p.y));
	}

	/** Calculate pairwise force */
	public double calcPairwiseForce(Planet p) {
		double gconst = 6.67e-11;
		double r = this.calcDistance(p);
		return gconst*this.mass*p.mass/(r*r);
	}

	/** Calculate pairwise force in X direction */
	public double calcPairwiseForceX(Planet p) {
		double force = this.calcPairwiseForce(p);
		double r = this.calcDistance(p);
		return force*(p.x - this.x)/r;
	}

	/** Calculate pairwise force in Y direction */
	public double calcPairwiseForceY(Planet p) {
		double force = this.calcPairwiseForce(p);
		double r = this.calcDistance(p);
		return force*(p.y - this.y)/r;
	}

	/** Calculate net force */
	public void setNetForce(Planet[] p) {
		this.xNetForce = 0.0;
		this.yNetForce = 0.0;
		for(int i = 0; i < p.length; i ++) {
			if(p[i] != this) {
				this.xNetForce += this.calcPairwiseForceX(p[i]);
				this.yNetForce += this.calcPairwiseForceY(p[i]);
			}
		}
	}

	/** Draw the planet in a 2D plane */
	public void drawIt() {
		StdDraw.picture(this.x, this.y, "/images/" + this.img);
	}

	/** Update planet state */
	public void update(double dt) {
		this.xAccel = this.xNetForce/this.mass;
		this.yAccel = this.yNetForce/this.mass;

		this.xVelocity += dt*this.xAccel;
		this.yVelocity += dt*this.yAccel;

		this.x += dt*this.xVelocity;
		this.y += dt*this.yVelocity;
	}

}
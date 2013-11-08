package util;

import java.awt.Point;

public class Part {

	//location
	private Point location;
	//self-reference for a connected part from the  up
	private Part up;
	//self-reference for a connected part from the  down
	private Part down;
	//self-reference for a connected part from the  left
	private Part left;
	//self-reference for a connected part from the right
	private Part right;

	// the constructors
	public Part(Point location, Part up, Part down, Part left, Part right) {
		super();
		this.location = location;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

	
	public Part(Point location) {
		super();
		this.location = location;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Part getUp() {
		return up;
	}

	public void setUp(Part up) {
		this.up = up;
	}

	public Part getDown() {
		return down;
	}

	public void setDown(Part down) {
		this.down = down;
	}

	public Part getLeft() {
		return left;
	}

	public void setLeft(Part left) {
		this.left = left;
	}

	public Part getRight() {
		return right;
	}

	public void setRight(Part right) {
		this.right = right;
	}
	
	// a compare method by location
	public boolean CompareParts(Part P)
	{
		if(this.location.x == P.location.x && this.location.y == P.location.y)
		{
			return true;
		}
		return false;
		
	}

}

package util;

import java.awt.Point;

public class Part {

	private Point location;
	private Part up;
	private Part down;
	private Part left;
	private Part right;

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
	
	public boolean CompareParts(Part P)
	{
		if(this.location.x == P.location.x && this.location.y == P.location.y)
		{
			return true;
		}
		return false;
		
	}

}

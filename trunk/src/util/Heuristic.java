package util;

import java.util.ArrayList;

import wamp.WampState;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.operation.distance.DistanceOp;

public class Heuristic {

	public static double returnHeuristic(SearchTreeNode node) {
		
		//centering property
		if(((WampState)node.getState()).getNumberOfConnectedParts() == ((WampState)node.getState()).getGrid().getParts().size() - 1)
		{
			return 0.0;
		}
		
		double result = 0.0;
		int PartSize = ((WampState)node.getState()).getGrid().getParts().size();
		
		for(Part p1 : ((WampState)node.getState()).getGrid().getParts())
		{
			for(Part p2 : ((WampState)node.getState()).getGrid().getParts())
			{
				if( p1 != p2)
				{
					PrecisionModel f1 = new PrecisionModel();
					result += DistanceOp.distance(new Point(new Coordinate(p1.getLocation().getX(),p1.getLocation().getY()),f1,0),
							new Point(new Coordinate(p2.getLocation().getX(),p2.getLocation().getY()), f1,0));
					
				}
			}
		}
		
		System.out.println("result :" + result + " Partsize: " + PartSize);
		return result / Math.pow(PartSize,2);
		/*
		if (((WampState)node.getState()).getGrid().getParts().size() == 2) {
			PrecisionModel f1 = new PrecisionModel();
			return DistanceOp.distance(new Point(
					new Coordinate(((WampState)node.getState()).getGrid().getParts().get(0).getLocation().getX(),
							((WampState)node.getState()).getGrid().getParts().get(0).getLocation().getY()), f1, 0), new Point(
								new Coordinate(((WampState)node.getState()).getGrid().getParts().get(1).getLocation().getX(),
										((WampState)node.getState()).getGrid().getParts().get(1).getLocation().getY()), f1, 0))/2;
		} else {
			ArrayList<Coordinate> Coordinates = new ArrayList<Coordinate>();
			for (Part p : ((WampState)node.getState()).getGrid().getParts()) {
				Coordinate coordinate = new Coordinate(p.getLocation().getX(),
						p.getLocation().getY());
				Coordinates.add(coordinate);
			}
			Coordinates.add(new Coordinate(((WampState)node.getState()).getGrid().getParts().get(0).getLocation().getX(),
					((WampState)node.getState()).getGrid().getParts().get(0).getLocation().getY()));

			PrecisionModel f1 = new PrecisionModel();
			Coordinate[] temp = new Coordinate[Coordinates.size()];
			temp = Coordinates.toArray(temp);
			LinearRing lr = new LinearRing(temp, f1, 0);
			Polygon poly = new Polygon(lr, f1, 0);
			Point result = poly.getCentroid();
			
			System.out.println("Centroid X = " + result.getX() + " Centroid Y = " + result.getY());
			
			
			Double resultSoFar = 0.0;
			for (int i = 0; i < Coordinates.size(); i++) {
				resultSoFar += DistanceOp.distance(result, new Point(
						Coordinates.get(i), f1, 0));
			}

			if(Coordinates.size() == 0){
				System.out.println("MINA!!!!!!");
				System.exit(0);
			}
			return resultSoFar; /// (Coordinates.size());
*/
		
		
	}

	public static double returnHeuristic2(SearchTreeNode node){

		
		ArrayList<ArrayList<Part>> bulks = new ArrayList<ArrayList<Part>>();
		ArrayList<Part> parts = ((WampState)node.getState()).getGrid().getParts();
		boolean found = false;
		for(Part p : parts){
			for(int i = 0;i<bulks.size();i++){
				ArrayList<Part> bulk = bulks.get(i); 
				if(bulk.contains(p)){
					found = true;
				}
			}
			if(bulks.size()==0 || !found){
				ArrayList<Part> result =new ArrayList<Part>();
				((WampState)node.getState()).getGrid().GetBulksRec(p, result);
				bulks.add(result);
			}
		}
		
		
		return bulks.size() -1;
	}
}

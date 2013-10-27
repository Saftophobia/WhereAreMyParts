package util;

import java.util.ArrayList;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.operation.distance.DistanceOp;

public class Heuristic {

	public static double returnHeuristic(ArrayList<Part> Parts) {
		if (Parts.size() == 2) {
			PrecisionModel f1 = new PrecisionModel();
			return DistanceOp.distance(new Point(
					new Coordinate(Parts.get(0).getLocation().getX(),
							Parts.get(0).getLocation().getY()), f1, 0), new Point(
								new Coordinate(Parts.get(1).getLocation().getX(),
										Parts.get(1).getLocation().getY()), f1, 0))/2;
		} else {
			ArrayList<Coordinate> Coordinates = new ArrayList<Coordinate>();
			for (Part p : Parts) {
				Coordinate coordinate = new Coordinate(p.getLocation().getX(),
						p.getLocation().getY());
				Coordinates.add(coordinate);
			}
			Coordinates.add(new Coordinate(Parts.get(0).getLocation().getX(),
					Parts.get(0).getLocation().getY()));

			PrecisionModel f1 = new PrecisionModel();
			Coordinate[] temp = new Coordinate[Coordinates.size()];
			temp = Coordinates.toArray(temp);
			LinearRing lr = new LinearRing(temp, f1, 0);
			Polygon poly = new Polygon(lr, f1, 0);
			Point result = poly.getCentroid();
			Double resultSoFar = 0.0;
			for (int i = 0; i < Coordinates.size() - 1; i++) {
				resultSoFar += DistanceOp.distance(result, new Point(
						Coordinates.get(i), f1, 0));
			}

			return resultSoFar / (Coordinates.size() - 1);

		}
	}
}

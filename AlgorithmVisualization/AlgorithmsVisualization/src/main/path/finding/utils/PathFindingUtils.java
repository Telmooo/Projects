package main.path.finding.utils;

import main.path.finding.PathFinding;
import main.utils.Coordinate;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 09-02-2019
 */
public class PathFindingUtils {

	public static Coordinate[] getNeighbours(final PathFinding pf, Coordinate node) {

		Coordinate[] neighbourhood = new Coordinate[8];
		int neighbours = 0;

		for (int yoff = -1; yoff < 2; yoff++) {
			for (int xoff = -1; xoff < 2; xoff++) {

				if ((xoff == 0 && yoff == 0) || node.x + xoff < 0 || node.x + xoff >= pf.cols || node.y + yoff < 0
						|| node.y + yoff >= pf.rows)
					continue;
				neighbourhood[neighbours] = new Coordinate(node.x + xoff, node.y + yoff);
				neighbours++;
			}
		}
		return neighbourhood;
	}

}

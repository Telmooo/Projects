package main.path.finding.algorithms;

import main.path.finding.PathFinding;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 09-02-2019
 */
public interface PathFinder {
	
	/**
	 * 
	 * @return
	 */
	String name();

	/**
	 * 
	 * @param pf
	 */
	void findPath(PathFinding pf);
}

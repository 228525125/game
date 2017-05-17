package org.cx.game.tools;

import java.awt.Point;
import java.util.LinkedList;

/**
 * 被用于Ground.route
 * @author chenxian
 *
 */
public class Node implements Comparable {
	public Point _Pos; // position of the node
	public int sourcePoint;
	public int destiPoint;
	// public Node _parentnode; //the parent node
	public Node _parentnode;

	private Node() {

	}

	// initialize the NOde
	public Node(Point _Pos) {
		this._Pos = _Pos;
	}

	// get the cost of the Path
	public int GetCost(Node node) {
		return CellularDistrict.getShortPathLength(CellularDistrict.pointToInteger(this._Pos.x, this._Pos.y), CellularDistrict.pointToInteger(node._Pos.x, node._Pos.y));
	}

	// check if the node is the destination point
	public boolean equals(Object node) {
		if (_Pos.x == ((Node) node)._Pos.x && _Pos.y == ((Node) node)._Pos.y) {
			return true;
		}
		return false;
	}

	// get the minist cost
	public int compareTo(Object node) {
		int a1 = sourcePoint + destiPoint;
		int a2 = ((Node) node).sourcePoint + ((Node) node).destiPoint;
		if (a1 < a2) {
			return -1;
		} else if (a1 == a2) {
			return 0;
		} else
			return 1;

	}

	public LinkedList getLimit() {
		LinkedList limit = new LinkedList();
		int x = _Pos.x;
		int y = _Pos.y;
		if(y%2==0){
			limit.add(new Node(new Point(x - 1, y - 1))); // leftup
			limit.add(new Node(new Point(x - 1, y + 1))); // leftdown
			limit.add(new Node(new Point(x - 1, y))); // left
			limit.add(new Node(new Point(x , y - 1))); // rightup
			limit.add(new Node(new Point(x , y + 1))); // rightdown
			limit.add(new Node(new Point(x + 1, y))); // right
		}else{
			limit.add(new Node(new Point(x, y - 1))); // leftup
			limit.add(new Node(new Point(x, y + 1))); // leftdown
			limit.add(new Node(new Point(x - 1, y))); // left
			limit.add(new Node(new Point(x + 1, y - 1))); // rightup
			limit.add(new Node(new Point(x + 1, y + 1))); // rightdown
			limit.add(new Node(new Point(x + 1, y))); // right
		}
		
		return limit;
	}
}

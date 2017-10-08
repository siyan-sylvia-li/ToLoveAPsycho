package com.example.sylviali.toloveapsycho;

import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;

public class Node implements Comparable{
	private String description;
	private String index;
	
	public Node() {
		this.description = "na";
		this.index = "0";
	}
	
	public Node(String description, String index) {
		this.description = description;
		this.index = index;
	}

	public String getDescription() {
		return description;
	}

	public String getIndex() {
		return index;
	}


	
	
	public String toString() {
		return this.description + "&" + this.index;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Node) {
			if ((((Node)other).description.equals(this.description))
				&& (((Node)other).index.equals(this.index))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(@NonNull Object o) {
		return Integer.parseInt(this.index) - Integer.parseInt(((Node)o).getIndex());
	}
}

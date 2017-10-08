package com.example.sylviali.toloveapsycho;

import android.support.annotation.NonNull;

public class Page implements Comparable {
	private Node[] optionNodes = new Node[3];

	public Node[] getOptionNodes() {
		return optionNodes;
	}

	public Node getMotherNode() {
		return motherNode;
	}

	private Node motherNode;
	
	public Page() {
		for (int i = 0; i < 3; i++) {
			optionNodes[i] = new Node();
		}
		motherNode = new Node();
	}
	
	public void setOptions(Node...nodes) {
		for (int i = 0; i < nodes.length; i++) {
			this.optionNodes[i] = nodes[i];
		}
	}
	
	public void addOption(Node node) {
		for (int i = 0; i < optionNodes.length; i++) {
			if (optionNodes[i].equals(new Node())) {
				optionNodes[i] = node;
				break;
			}
		}
	}
	
	public String toString() {
		String optionStr = "";
		for (Node n : optionNodes) {
			optionStr += n.toString();
			optionStr += "&";
		}
		return (motherNode.toString() + "&"
				 + optionStr.substring(0, optionStr.length() - 1));
	}
	public void setMother(Node node) {
		this.motherNode = node;
	}

	@Override
	public int compareTo(@NonNull Object o) {
		return motherNode.compareTo(((Page)o).getMotherNode());
	}
}

package cz.jlochman.aoc2017.day07;

import java.util.HashSet;
import java.util.Set;

class Node {

   private String    name;
   private int       selfWeight;
   private int       totalWeight;
   private Set<Node> children = new HashSet<>();
   private Node      parent   = null;

   public Node(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Set<Node> getChildren() {
      return children;
   }

   public void setChildren(Set<Node> children) {
      this.children = children;
   }

   public Node getParent() {
      return parent;
   }

   public void setParent(Node parent) {
      this.parent = parent;
   }

   public int getSelfWeight() {
      return selfWeight;
   }

   public void setSelfWeight(int selfWeight) {
      this.selfWeight = selfWeight;
   }

   public void addChild(Node child) {
      this.children.add(child);
      child.setParent(this);
   }

   public int getTotalWeight() {
      return totalWeight;
   }

   public void setTotalWeight(int totalWeight) {
      this.totalWeight = totalWeight;
   }

}

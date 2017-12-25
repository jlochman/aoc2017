package cz.jlochman.aoc2017.day24;

import java.util.HashSet;
import java.util.Set;

class Component {

   private int port1;
   private int port2;

   private Component      parent   = null;
   private Set<Component> children = new HashSet<>();

   public Component(int port1, int port2) {
      this.port1 = port1;
      this.port2 = port2;
   }

   public int getPort1() {
      return port1;
   }

   public int getPort2() {
      return port2;
   }

   public int getOtherPort(int port) {
      if (port == port1) {
         return port2;
      } else if (port == port2) {
         return port1;
      } else {
         throw new IllegalArgumentException("invalid port number");
      }
   }

   public Component getParent() {
      return parent;
   }

   public void setParent(Component parent) {
      this.parent = parent;
   }

   public Set<Component> getChildren() {
      return children;
   }

   public void setChildren(Set<Component> children) {
      if (children != null) {
         children.stream()
                 .forEach(ch -> ch.setParent(this));
      }
      this.children = children;
   }

   public void setPort1(int port1) {
      this.port1 = port1;
   }

   public void setPort2(int port2) {
      this.port2 = port2;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Component) {
         Component other = (Component) obj;
         return (this.getPort1() == other.getPort1()
                 && this.getPort2() == other.getPort2())
                || (this.getPort1() == other.getPort2()
                    && this.getPort2() == other.getPort1());
      }
      return super.equals(obj);
   }

   @Override
   public int hashCode() {
      return 1000 * Math.max(this.getPort1(), this.getPort2())
             + Math.min(this.getPort1(), this.getPort2());
   }

}

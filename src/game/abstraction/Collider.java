package game.abstraction;

import java.util.Collection;

public abstract class Collider extends Root {
     protected int x;
     protected int y;
     protected int height;
     protected int width;

     public Collider(int x, int y) {
          this.x = x;
          this.y = y;
     }

     public void onCollide(GameObject target) {}

     public boolean collidesWith(GameObject target) {
          return (this.x < target.getX() + target.getWidth() &&
                  this.x + this.getWidth() > target.getX() &&
                  this.y < target.getY() + target.getHeight() &&
                  this.y + this.getHeight() > target.getY());
     }

     public boolean collidesWith(Collection<? extends GameObject> targets) {
          return targets.stream().anyMatch(this::collidesWith);
     }

     public int getX() {
          return x;
     }

     public int getY() {
          return y;
     }

     public int getWidth() {
          return width;
     }

     public int getHeight() {
          return height;
     }
}

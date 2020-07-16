package game.abstraction;

import java.util.Collection;

public abstract class Collider extends Root {
     protected int x;
     protected int y;
     protected int height;
     protected int width;

     public Collider(int x, int y, int width, int height) {
          this.x = x;
          this.y = y;
          this.height = height;
          this.width = width;
     }

     public void onCollide(Entity target) {}

     public boolean collidesWith(Entity target) {
          return (this.x < target.getX() + target.getWidth() &&
                  this.x + this.getWidth() > target.getX() &&
                  this.y < target.getY() + target.getHeight() &&
                  this.y + this.getHeight() > target.getY());
     }

     public boolean collidesWith(Collection<? extends Entity> targets) {
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

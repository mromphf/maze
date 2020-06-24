package game;

public abstract class GameObject implements Drawable {
     protected int x;
     protected int y;
     protected int height;
     protected int width;

     public GameObject(int x, int y) {
          this.x = x;
          this.y = y;
     }

     public boolean collidesWith(GameObject target) {
          return (this.x < target.x + target.width &&
                  this.x + this.width > target.x &&
                  this.y < target.y + target.height &&
                  this.y + this.height > target.y);
     }
}

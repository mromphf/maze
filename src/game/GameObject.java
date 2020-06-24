package game;

public abstract class GameObject {
     protected int x;
     protected int y;
     protected int height;
     protected int width;

     public GameObject(int x, int y) {
          this.x = x;
          this.y = y;
     }
}

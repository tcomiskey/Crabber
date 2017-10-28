import java.swing.*;

public class Human {
    private ImageIcon image;
    
    public Enemy(int boardWidth, int fileName){
        super(boardWidth, fileName);
    }
              
    public Enemy(int boardWidth){
            super(boardWidth);
    }
    
    public int getX(){
        return xLoc;
    }//getX
    
    public int getY(){
        return yLoc;
    }//getY
    
    public void setX(int x){
        xLoc = x;
    }//setX
    
    public void setY(int y){
        yLoc = y;
    }//setY
    
    public void setDir(int dir){
        this.dir = dir;
    }//setDir
    
    public ImageIcon getImage(){
        return image;
    }//getImage

}
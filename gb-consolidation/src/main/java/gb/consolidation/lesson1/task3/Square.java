package gb.consolidation.lesson1.task3;

public class Square extends Figure{

    public Square(int area) {
        super(area);
    }

    @Override
    void draw() {
        System.out.println("Draw a square with an area of " + area);
    }
}

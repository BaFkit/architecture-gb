package gb.consolidation.lesson1.task3;

public class Triangle extends Figure{

    public Triangle(int area) {
        super(area);
    }

    @Override
    void draw() {
        System.out.println("Draw a triangle with an area of " + area);
    }
}

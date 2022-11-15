package gb.consolidation.lesson1.task3;

public class Circle extends Figure{

    public Circle(int area) {
        super(area);
    }

    @Override
    void draw() {
        System.out.println("Draw a circle with an area of " + area);
    }
}

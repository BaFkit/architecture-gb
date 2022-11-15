package gb.consolidation.lesson1.task3;

public class Main {

    public static void main(String[] args) {

        Figure circle = new Circle(10);
        Figure square = new Square(4);
        Figure triangle = new Triangle(7);

        circle.draw();
        square.draw();
        triangle.draw();

    }
}

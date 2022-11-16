package gb.consolidation.lesson1.task3;

public abstract class Figure {

    protected int area;

    public Figure(int area) {
        this.area = area;
    }

    abstract void draw();
}

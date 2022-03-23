package math;

class Value {

    @Override
    public String toString() {
        return "Value";
    }
}

class Power extends Value{
    @Override
    public String toString() {
        return "Power";
    }
}

class Divide extends Value{

    @Override
    public String toString() {
        return "Divide";
    }
}

class Multiply extends Value{

    @Override
    public String toString() {
        return "Multiply";
    }
}

class Subtract extends Value{

    @Override
    public String toString() {
        return "Subtract";
    }
}

class Add extends Value{

    @Override
    public String toString() {
        return "Add";
    }
}

class Number extends Value{

    String number;

    public Number(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number;
    }
}
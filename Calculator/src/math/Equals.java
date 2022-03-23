package math;

import java.util.ArrayList;

class Equals extends Value{

    ArrayList<Value> values;

    public Equals() {
        values = new ArrayList<>();
    }

    @Override
    public String toString() {
        try {
            power();
            multiplyOrDivide();
            addOrSubtract();
        }catch (Exception e){
            throw new NumberFormatException("Check format ot the mathematical expression!");
        }
        return values.get(0).toString();
    }

    public void parseString(String input)throws NumberFormatException{
        try {
            String[] characters = new String[input.length()];
            for(int i = 0; i < input.length();i++){
                characters[i] = input.substring(i, i+1);
            }
            String currentNumber = "";
            int bracketsStart = -1, bracketsEnd = 0;
            int bracketsOpenedCount = 0;
            for (int i = 0; i < characters.length; i++) {
                if(characters[i].equals("(")){
                    if(bracketsOpenedCount == 0){
                        bracketsStart = i;
                    }
                    bracketsOpenedCount++;
                }
                if(bracketsOpenedCount != 0){
                    if(characters[i].equals(")")){
                        bracketsOpenedCount--;
                        if(bracketsOpenedCount == 0){
                            bracketsEnd = i;
                        }
                    }
                }
                if(bracketsEnd != 0 || (i == characters.length - 1 && bracketsOpenedCount != 0)){
                    Equals e = new Equals();
                    if(bracketsEnd == 0){
                        e.parseString(input.substring(bracketsStart+1, i+1));
                        bracketsOpenedCount = 0;
                    }else
                        e.parseString(input.substring(bracketsStart+1, bracketsEnd));
                    values.add(e);
                    bracketsEnd = 0;
                }
                if(bracketsOpenedCount == 0){
                    if(characters[i].equals("*")){
                        if(!currentNumber.equals("")){
                            values.add(new Number(currentNumber));
                        }
                        currentNumber="";
                        values.add(new Multiply());
                    }else if(characters[i].equals("+")){
                        if(!currentNumber.equals("")){
                            values.add(new Number(currentNumber));
                        }
                        currentNumber="";
                        values.add(new Add());
                    }else if(characters[i].equals("/")){
                        if(!currentNumber.equals("")){
                            values.add(new Number(currentNumber));
                        }
                        currentNumber="";
                        values.add(new Divide());
                    }else if(characters[i].equals("-")){
                        if(!currentNumber.equals("")){
                            values.add(new Number(currentNumber));
                        }
                        currentNumber="";
                        values.add(new Subtract());
                    }else if(characters[i].equals("^")){
                        if(!currentNumber.equals("")){
                            values.add(new Number(currentNumber));
                        }
                        currentNumber="";
                        values.add(new Power());
                    }else{
                        if(!characters[i].equals(")")){
                            currentNumber+=characters[i];
                        }
                    }
                }
            }
            if(!currentNumber.equals("")){
                values.add(new Number(currentNumber));
            }
        }catch (Exception e){
            throw new NumberFormatException("Check format ot the mathematical expression!");
        }
    }

    private void power() throws Exception{
        boolean containsPower = true;
        while (containsPower){
            containsPower = false;
            for(int i = 0; i < values.size();i++){
                if(values.get(i).getClass().equals(Power.class)){
                    containsPower = true;
                    values.set(i, new Number(""+(Math.pow(Double.parseDouble(values.get(i-1).toString()), Double.parseDouble(values.get(i+1).toString())))));
                    values.remove(i+1);
                    values.remove(i-1);
                    break;
                }
            }
        }
    }

    private void multiplyOrDivide() throws Exception{
        boolean containsMultiplicationOrDivision = true;
        while (containsMultiplicationOrDivision){
            containsMultiplicationOrDivision = false;
            for(int i = 0; i < values.size();i++){
                if(values.get(i).getClass().equals(Multiply.class)){
                    containsMultiplicationOrDivision = true;
                    values.set(i, new Number(""+(Double.parseDouble(values.get(i-1).toString()) * Double.parseDouble(values.get(i+1).toString()))));
                    values.remove(i+1);
                    values.remove(i-1);
                    break;
                }else if(values.get(i).getClass().equals(Divide.class)){
                    containsMultiplicationOrDivision = true;
                    values.set(i, new Number(""+(Double.parseDouble(values.get(i-1).toString()) / Double.parseDouble(values.get(i+1).toString()))));
                    values.remove(i+1);
                    values.remove(i-1);
                    break;
                }
            }
        }
    }

    private void addOrSubtract() throws Exception{
        boolean containsAdditionOrSubtraction = true;
        while (containsAdditionOrSubtraction){
            containsAdditionOrSubtraction = false;
            for(int i = 0; i < values.size();i++){
                if(values.get(i).getClass().equals(Add.class)){
                    containsAdditionOrSubtraction = true;
                    values.set(i, new Number(""+(Double.parseDouble(values.get(i-1).toString()) + Double.parseDouble(values.get(i+1).toString()))));
                    values.remove(i+1);
                    values.remove(i-1);
                    break;
                }else if(values.get(i).getClass().equals(Subtract.class)){
                    containsAdditionOrSubtraction = true;
                    values.set(i, new Number(""+(Double.parseDouble(values.get(i-1).toString()) - Double.parseDouble(values.get(i+1).toString()))));
                    values.remove(i+1);
                    values.remove(i-1);
                    break;
                }
            }
        }
    }

    public void printList(){
        for (int i = 0; i < values.size(); i++) {
            if(!values.get(i).getClass().equals(Equals.class)){
                System.out.println(values.get(i).toString());
            }
        }
    }

}

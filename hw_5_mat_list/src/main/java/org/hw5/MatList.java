package org.hw5;

public class MatList<E extends Number> {
    private final int INIT_SIZE = 16;
    private Object[] numbers = new Object[INIT_SIZE];
    private int pointer = 0;

    public String getNumbers() {
        for (Object n :
                numbers) {
            if (n != null) {
                System.out.println(n);
            }
        }
        return "MatList output";
    }

    public void add(E n) {
        if (pointer >= numbers.length - 1)
            resize(numbers.length * 2);
        numbers[pointer++] = n;
    }

    public void add(E... n) {
        resize(n.length);
        if (pointer >= numbers.length - 1)
            resize(numbers.length * 2);
        for (E g :
                n) {
            numbers[pointer++] = g;
        }
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(numbers, 0, newArray, 0, pointer);
        numbers = newArray;
    }

    public void join(MatList... ml) {
        int allLength = 0;
        for (MatList n :
                ml) {
            allLength += n.numbers.length;
        }
        MatList matList = new MatList();
        matList.resize(allLength);
        matList.numbers = this.numbers;
        matList.pointer = this.pointer;
        for (MatList n :
                ml) {
            for (int i = 0; i < n.numbers.length; i++) {
                matList.add((Number) n.numbers[i]);
            }
        }
        this.numbers = matList.numbers;
    }

    public void intersection(MatList... ml) {
        int allLength = 0;
        for (MatList n :
                ml) {
            allLength += n.numbers.length;
        }
        MatList matList = new MatList();
        matList.resize(allLength);
        for (int i = 0; i < this.numbers.length; i++) {
            int counter = 0;
            for (int j = 0; j < matList.numbers.length; j++) {
                if (this.numbers[i] == matList.numbers[j]) {
                    counter++;
                }
            }
            if (counter == 0) {
                matList.add((Number) this.numbers[i]);
            }
            counter = 0;
        }
        for (MatList n :
                ml) {
            for (int i = 0; i < n.numbers.length; i++) {
                int counter = 0;
                for (int j = 0; j < matList.numbers.length; j++) {
                    if (n.numbers[i] == matList.numbers[j]) {
                        counter++;
                    }
                }
                if (counter == 0) {
                    matList.add((Number) n.numbers[i]);
                }
            }
        }
        this.numbers = matList.numbers;
    }

    public void sortAsc() {
        int index = -1;
        for (int i = 0; i < numbers.length; i++) {
            index = i;
            for (int j = i; j <= numbers.length - 1; j++) {
                if (numbers[j] != null && numbers[index] != null) {
                    if (Integer.valueOf(numbers[j].toString()) < Integer.valueOf(numbers[index].toString())) {
                        index = j;
                    }
                }
            }
            if (numbers[i] != null) {
                int temp = Integer.valueOf(numbers[i].toString());
                numbers[i] = numbers[index];
                numbers[index] = temp;
            }
        }
    }

    public void sortAsc(int first, int last) {
        int index = -1;
        int counter = 0;
        Object[] newNumbers = new Object[last - first];
        for (int i = first; i < last; i++) {
            newNumbers[counter] = numbers[i];
            counter++;
        }
        for (int i = 0; i < newNumbers.length; i++) {
            index = i;
            for (int j = i; j <= newNumbers.length - 1; j++) {
                if (numbers[j] != null && numbers[index] != null) {
                    if (Integer.valueOf(newNumbers[j].toString()) < Integer.valueOf(newNumbers[index].toString())) {
                        index = j;
                    }
                }
            }
            if (numbers[i] != null) {
                int temp = Integer.valueOf(newNumbers[i].toString());
                newNumbers[i] = newNumbers[index];
                newNumbers[index] = temp;
            }
        }
        counter = 0;
        for (int i = first; i < last; i++) {
            numbers[i] = newNumbers[counter];
            counter++;
        }
    }

    public void sortAsc(E value) {
        int valueIndex = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (Integer.valueOf(value.toString()) == numbers[i]) {
                valueIndex = i;
            }
        }
        int index = -1;
        for (int i = valueIndex; i < numbers.length; i++) {
            index = i;
            for (int j = i; j <= numbers.length - 1; j++) {
                if (numbers[j] != null && numbers[index] != null) {
                    if (Integer.valueOf(numbers[j].toString()) < Integer.valueOf(numbers[index].toString())) {
                        index = j;
                    }
                }
            }
            if (numbers[i] != null) {
                int temp = Integer.valueOf(numbers[i].toString());
                numbers[i] = numbers[index];
                numbers[index] = temp;
            }
        }
    }


    public void sortDesc() {
        int index = -1;
        for (int i = 0; i < numbers.length; i++) {
            index = i;
            for (int j = i; j <= numbers.length - 1; j++) {
                if (numbers[j] != null && numbers[index] != null) {
                    if (Integer.valueOf(numbers[j].toString()) > Integer.valueOf(numbers[index].toString())) {
                        index = j;
                    }
                }
            }
            if (numbers[i] != null) {
                int temp = Integer.valueOf(numbers[i].toString());
                numbers[i] = numbers[index];
                numbers[index] = temp;
            }
        }
    }

    public void sortDesc(int first, int last) {
        int index = -1;
        int counter = 0;
        Object[] newNumbers = new Object[last - first];
        for (int i = first; i < last; i++) {
            newNumbers[counter] = numbers[i];
            counter++;
        }
        for (int i = 0; i < newNumbers.length; i++) {
            index = i;
            for (int j = i; j <= newNumbers.length - 1; j++) {
                if (numbers[j] != null && numbers[index] != null) {
                    if (Integer.valueOf(newNumbers[j].toString()) > Integer.valueOf(newNumbers[index].toString())) {
                        index = j;
                    }
                }
            }
            if (numbers[i] != null) {
                int temp = Integer.valueOf(newNumbers[i].toString());
                newNumbers[i] = newNumbers[index];
                newNumbers[index] = temp;
            }
        }
        counter = 0;
        for (int i = first; i < last; i++) {
            numbers[i] = newNumbers[counter];
            counter++;
        }
    }

    public void sortDesc(E value) {
        int valueIndex = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (Integer.valueOf(value.toString()) == numbers[i]) {
                valueIndex = i;
            }
        }
        int index = -1;
        for (int i = valueIndex; i < numbers.length; i++) {
            index = i;
            for (int j = i; j <= numbers.length - 1; j++) {
                if (numbers[j] != null && numbers[index] != null) {
                    if (Integer.valueOf(numbers[j].toString()) > Integer.valueOf(numbers[index].toString())) {
                        index = j;
                    }
                }
            }
            if (numbers[i] != null) {
                int temp = Integer.valueOf(numbers[i].toString());
                numbers[i] = numbers[index];
                numbers[index] = temp;
            }
        }
    }

    Number get(int index) {
        return (Number) numbers[index];
    }

    Number getMax() {
        Object max = 0;
        if (numbers.length == 0) {
            return (Number) max;
        }
        if (numbers.length >= 1) {
            max = numbers[0];
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null) {
                if (Integer.valueOf(max.toString()) < Integer.valueOf(numbers[i].toString())) {
                    max = (int) numbers[i];
                    System.out.println(max);
                }
            }
        }
        System.out.println();
        return (Number) max;
    }

    Number getMin() {
        Object max = 0;
        if (numbers.length == 0) {
            return (Number) max;
        }
        if (numbers.length >= 1) {
            max = numbers[0];
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null) {
                if (Integer.valueOf(max.toString()) > Integer.valueOf(numbers[i].toString())) {
                    max = (int) numbers[i];
                    System.out.println(max);
                }
            }
        }
        System.out.println();
        return (Number) max;
    }

    Number getAverage() {
        double sum = 0;
        int counter = 0;
        for (Object n :
                numbers) {
            if (n != null) {
                sum += (int) n;
                counter++;
            }
        }
        return sum / counter;
    }

    Number getMedian() {
        int med = 0;
        int counter = 0;
        for (Object n :
                numbers) {
            if (n != null) {
                counter++;
            }
        }
        if (numbers.length % 2 == 0 && numbers.length != 0) {
            med = ((int) numbers[counter / 2 - 1] + (int) numbers[counter / 2]) / 2;
        } else {
            if (numbers.length != 0) {
                med = (int) numbers[counter / 2];
            }
        }
        return med;
    }

    Number[] toArray() {
        Number[] newArr = new Number[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            newArr[i] = (Number) numbers[i];
        }
        return newArr;
    }

    Number[] toArray(int first, int last) {
        Number[] newArr = new Number[numbers.length];
        int counter = 0;
        for (int i = 0; i < first; i++) {
            newArr[counter] = (Number) numbers[i];
            counter++;
        }
        for (int i = last; i < numbers.length; i++) {
            newArr[counter] = (Number) numbers[i];
            counter++;
        }
        return newArr;
    }

    MatList cut(int firstIndex, int lastIndex) {
        MatList matList = new MatList();
        int counter = 0;
        for (int i = 0; i < firstIndex; i++) {
            matList.numbers[counter] = this.numbers[i];
            counter++;
        }
        for (int i = lastIndex; i < this.numbers.length; i++) {
            matList.numbers[counter] = this.numbers[i];
            counter++;
        }
        return matList;
    }

    void clear() {
        this.numbers = new Object[INIT_SIZE];
    }

    void clear(Number[] numbers) {
        for (int i = 0; i < this.numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (this.numbers[i] == numbers[j]) {
                    this.numbers[i] = null;
                }
            }
        }
    }

    public MatList() {

    }

    public MatList(E[]... numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                add(numbers[i][j]);
            }
        }
    }

    public MatList(MatList... numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].numbers.length; j++) {
                add((E) numbers[i].numbers[j]);
            }
        }
    }
}
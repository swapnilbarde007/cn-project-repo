package dsa.oops;

class DynamicArray {
    private int data[];
    private int nextElementIndex;

    public DynamicArray() {
        data = new int[5];
        nextElementIndex = 0;
    }

    public int size() {
        return nextElementIndex;
    }

    public boolean isEmpty() {
        return nextElementIndex == 0;
    }

    public int get(int i) {
        if (i >= nextElementIndex) {
            return -1;
        } else {
            return data[i];
        }
    }

    public boolean set(int idx, int val) {
        if (idx >= nextElementIndex) {
            return false;
        }
        this.data[idx] = val;
        return true;
    }

    public int add(int val) {
        if (nextElementIndex < this.data.length - 1) {
            this.data[nextElementIndex] = val;
            nextElementIndex++;
            return nextElementIndex - 1;
        } else {
            int[] tmpData = this.data;
            this.data = new int[data.length + 5];
            int ctr = 0;
            for (int x : tmpData) {
                this.data[ctr] = tmpData[ctr];
                ctr++;
            }
            this.data[nextElementIndex] = val;
            nextElementIndex++;
            return nextElementIndex - 1;
        }
    }

    public boolean removeLast() {
        this.data[nextElementIndex - 1] = 0;
        nextElementIndex--;
        if (nextElementIndex % 5 == 0) {
            int[] tmpData = this.data;
            int newDataLenght = this.data.length - 5;
            this.data = new int[newDataLenght];
            int ctr = 0;
            while (ctr < newDataLenght) {
                this.data[ctr] = tmpData[ctr];
                ctr++;
            }
        }

        return true;
    }
}
import java.util.Arrays;
public class ArrayList<T extends Comparable<T>> implements List<T> {
    private T[] data; // our generic array
    private int size; // size of our array
    private boolean isSorted; // is sorted variable.
    public ArrayList() { // Constructor for arraylist.
        data = (T[]) new Comparable[2];
        isSorted = true;
    }
    public void expand() { // this is a helper function that copies the given array and expands it by a size of *2
        T[] data2 = (T[]) new Comparable[data.length * 2];
        System.arraycopy(data, 0, data2, 0, size);
        data = data2; // sets temp data2 array to data original array.
        }
        public void sortCheck() { // helper function called sort check which is called whenever we are changing the order of the array.

            for (int j = 1; j < size; j++) { // iterates through the entire array
                if (data[j - 1].compareTo(data[j]) > 0) { // compares the previous value with the next and see if the values to the left is larger.
                    isSorted = false;
                    return;
                }
            }
            isSorted = true;
        }
    public boolean add(T element) { //adds a method to the end of the given generic array.
        if (element == null) { //null catch
            return false;
        } else if (data.length == size) { // if size of array is full
            expand();
            data[size] = element;
            size++;
        } else { // else we just add element to the end of the array and ++ the size
            data[size] = element;
            size++;
        }
        if (data[0] == null) {
            return true;
        }
        sortCheck(); // calls sort check helper function
        return true;
    }
    public boolean add(int index, T element) {
        if (element == null || index >= size || index < 0) { // null catch and if index given is out of bounds exceptions.
            return false;
        } else if (data.length == size) { // if array is full, then resize it and then add element at index and shift
            expand();
            for (int i = size; i >= index; i--) {
                data[i + 1] = data[i];
            }
            data[index] = element;
            size++;
        } else { // if array isn't full, then we can just add element and shift other elements.
            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
            data[index] = element;
            size++;
        }
            sortCheck(); //calls sort check.
        return true;
    }
    public void clear() { // sets each value in the array to null as a baseline and sets size to 0, and then it is known that its sorted.
        Arrays.fill(data, null);
        size =0;
        isSorted = true;
    }
    public T get(int index) { // Gives element of the given index.
        if (index < 0 || index >= data.length) // index out of range catcher
            return null;
        return data[index];
    }
    public int indexOf(T element) { // Index of method.
        if (element == null)
            return -1; // if element is null, then it is not a valid element of the array
        if (isSorted) { //sorted array so use binary search ie cut array in half and compare middle value to element T.
            int i = 0;
            while (i < size() && data[i].compareTo(element) <= 0) { // checks if the size is less than the counter and
                if (data[i] == element) {
                    return i;
                }
                i++;
            }
            return -1;
        } else { //non sorted array, so use linear search
            for (int i = 0; i < size; i++) {
                if (data[i] == element)
                    return i;
            }
        }
        return -1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void sort() { // not working (possibly because isSorted is incorrect)
        if (!isSorted) {
            for (int i = size; i >= 1; i--) { // Iterates through array by cutting off last element 1 at a time.
                for (int j = 0; j < i - 1; j++) { // Inner loop that represents the checking of each bubble sort between the two elements
                    if (data[j].compareTo(data[j+1]) > 0) { // Checks if the element to the left is larger than the right, if it is then goes into conditional.
                        T temp = data[j]; // These lines swap those two values using an intermediate temp T.
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                    }
                }
            }
            isSorted = true;
        }
    }
    public T remove(int index) {
        if (index < 0 || index >= size) { //checks for outside of indexes and returns null if the case.
            return null;}
        T element = data[index]; // this is getting the info from the removed and saving it for when it returned.
        for (int i = index; i < size() - 1; i++) {
            data[i] = data[i + 1]; // iterates
        }
        data[size() - 1] = null;
        size--;
        sortCheck(); // calls sort check on the method
        return element; // returns the element we removed.
    }
    public void removeDuplicates() {
        for (int i = 0; i < size; i++) { // iterates through the size of the array
            for (int j = i + 1; j<size; j++) { // iterates through the element of i to the end of the array
                if (data[i].compareTo(data[j]) == 0) { // compares each of those values with the previous ones.
                    remove(j); // calls remove method on j index.
                    j--;
                }
            }
        }
        sortCheck();
    }
    public void reverse() {
        if (size > 1) { //as long as there is more than 1 element in the array.
            for (int i = 0; i < size/2; i++) { //go through the list halfway up
                T temp = data[i]; // These 3 lines are taking the value of the data[i] then swapping the two before putting the element at data[size-i-1] to the next value in the array.
                data[i] = data[size -1 -i];
                data[size - 1-i] = temp;
            }
        }
        sortCheck();
    }
    public void exclusiveOr(List<T> otherList) {
        ArrayList<T> other = (ArrayList<T>) otherList; // sets up other array
        ArrayList<T> result = new ArrayList<>();
        if (otherList == null) // if the other list is null then the exclusive of either list is just this.list
            return;
        this.sort(); // these 4 lines are the sorting and removing duplicates of each list.
        this.removeDuplicates();
        other.sort();
        other.removeDuplicates();
        int i = 0, j = 0; // initializing variables used later in program
        while (i <this.size() && j <other.size()) { // while each of the variables less than the size meaning it will go to the end of the array.
            T current = this.get(i); // sets variables for the saved info of T at i and j index.
            T second = other.get(j);
            int comparison = current.compareTo(second);
            if (comparison < 0) { // if the comparison which is current.compare to(second), is less that zero meaning that current is less than second then we increment i
                result.add(current);
                i++;}
            else if (comparison > 0) { // same but increment j since cur greater than second
                result.add(second);
                j++;}
            else { // if they are equal then increment both.
                i++;
                j++;}
            } // reached the end of either both or one of the lists
        while (i < this.size()) { // if there are elements left in this then we add the rest to the array
            result.add(this.get(i));
            i++;
        }
        while (j < other.size()) { // if more in j then we add the rest of the other list to the result list.
            result.add(other.get(j));
            j++;
        }
        this.clear(); // we clear this.
        for(T item: result.data) { //then we add all the elements of our result into add.
            this.add(item);
        }
        }
    public T getMin() { // finds smallest value in array.
        if (size == 0) // special case on nothing in the array so no min.
            return null;
        T min = data[0]; // dummy value
        if (isSorted) // if is sorted we know that the min is data[0] or the first element of the array.
            return min;
        else { // If isn't sorted we have to check through whole array
            for (int i = 1; i<size;i++) { // iterates through list and finds smallest value.
                if (min.compareTo(data[i]) > 0)
                    min = data[i];
            }
        }
        return min;
    }
    public T getMax() { // Is checking for which value in the list is the max
        if (size == 0)
            return null;
        T max = data[0]; // sets max to a dummy value
        if (isSorted) // if the list is sorted we know that size-1 is the largest value in data array.
            return data[size-1];
        else {
            for (int i = 1; i < size; i++) { // iterates through the list
                if (max.compareTo(data[i]) < 0) // compares each value of the list and finds the largest essentially.
                    max = data[i];
                }
            }
        return max;
    }
    public String toString() { // prints out the to string of our list on a new line of each.
        StringBuilder s = new StringBuilder();
        for (int i = 0; i <size; i++) { // for loop that goes through whole length of array
            s.append(data[i].toString()).append("\n"); // appends the data types to string and then a newline character
        }
        return s.toString(); // returns the to string after all elements printed.
    }
    public boolean isSorted(){
        return isSorted;
    } // returns isSorted which is updated throughout the program.
}

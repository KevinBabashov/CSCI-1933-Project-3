public class LinkedList<T extends Comparable<T>> implements List<T>{
    private Node<T> start; //start initialized to the beginning of the linked list
    private int size; // size keeps track of size or how many nodes in linked list
    private boolean isSorted; // whether the linked list is sorted.
    public LinkedList(){      
        isSorted = true;
    } // constructor of linked list which simply makes isSorted true.
    public void sortCheck() { // sort check is called whenever we need to figure out whether calling a method changed the sort of the linked list
        if (start == null) { // if start is equal to null then we have no elements, so its already sorted and we just return
            return;
        }
        Node<T> currNode = start; // initialize nodes for tracking
        Node<T> nextNode = start.getNext();
                while (nextNode != null) { // while loop goes until the end of the linked list
                if(currNode.getData().compareTo(nextNode.getData()) > 0) { // compares if linked list not in ascending order and if so then sort is false;
                    isSorted = false;
                    return;
                }
                nextNode = nextNode.getNext();
                currNode = currNode.getNext();
        }
        isSorted = true;
    }

    public boolean add(T element){ // adds elements to the end of the linked list //**
        Node<T> ptr = start; // initializes variable
        if (element == null) { // if element is null then we aren't going to add it to the list.
            return false;
        }
        else if (start == null){ // if start is null then we need to make a new node of element and make that start
            start = new Node<>(element);
            size++; //Come back to line
        }
        else{ // if we don't have an empty linked list then we need to go until the end and once we are at the end set ptr.setNext to the node with data element.
            while (ptr.getNext() != null){
                ptr = ptr.getNext();
            }
            ptr.setNext(new Node<>(element));
            size++;

        }
        sortCheck(); // calls sort check after this add.
        return true;
    }

    public boolean add(int index, T element){ // adds element at index T. //**
        int currIndex = 0;
        Node<T> ptr = start; // initializes variables we will need to use.
        Node<T> temp;
        if (element == null || index < 0 || index >= size){ // if index out of bounds or element null then return false.
            return false;
        }
        else if (index ==0) { // if index is zero then we make a new node and set next to start to add it before so its index 0.
            Node<T> newNode = new Node<>(element);
            newNode.setNext(start);
            start = newNode;
            size++;
        }
        else{ // if index isn't zero.
            while (ptr.getNext() != null){ //traverses linked list
                if (currIndex == index - 1){ // checks if curr index which is being incremented each loop is equal to index - 1
                    temp = ptr.getNext();  // saving info
                    Node<T> Element = new Node<>(element, temp); // makes new node of data element with next of temp
                    ptr.setNext(Element); // sets next of ptr to element, so we preserve the linked list with the added node.
                    size++;
                    break;
                }
                currIndex++;
                ptr = ptr.getNext();
            }
        }
        sortCheck();
        return true;
    }

    public void clear(){ // clears linked list by simply severing the pointer of start to the node and then doing cleanup of is sorted and size.
        start = null;
        isSorted = true;
        size = 0;
    }

    public T get(int index){ //**
        int currIndex = 0;
        if (index < 0 || index >= size()) //if index out of range returns null
            return null;
        Node<T> ptr = start; // initializes variables
        T element;
        while (ptr != null){ //traverses list
            if(currIndex < index){ // if it's less than the curr index then increment index and pointer.
                currIndex++;
                ptr = ptr.getNext();
            }
            else{ // the first instance of currIndex !< index is when its equal, so we can just get the data from that ptr.get data().
                element = ptr.getData();
                return element;
            }
        }
        return null;
    }
    public int indexOf(T element){
        int currIndex = 0; // setting variables
        Node<T> ptr = start;
        if (element == null){ // if element is null then return -1.
            return -1;
        }
        while(ptr != null) { //traverses linked list
            if (element.compareTo(ptr.getData()) == 0) { // compares to check if element is equal to value in list
                return currIndex;
        }
            currIndex++;
            ptr = ptr.getNext();
        }
        return -1; // if it cant find a value that does then returns -1.
    }

    public boolean isEmpty(){
        return start == null;
    } // checks if start is equal to null, so returns true if start == null which means that its empty.

    public int size(){
        return size;} // returns size

    public void sort(){ // sorts list //**
        if (isSorted || start == null) { // if already sorted, end program.
            return;
        }
        Node<T> curr = start; // initialize variables
        Node<T> next;
        T temp;
        while (curr != null) { // traverse list
            next = curr.getNext();
            while (next != null) { // traverse next which is node after curr
                if (curr.getData().compareTo(next.getData()) > 0) { // compares the two nodes
                    temp = curr.getData(); // if different then we get temp of data of curr
                    curr.setData(next.getData()); // set curr node data to next data
                    next.setData(temp); // set next node data to curr node data
                }
                    next = next.getNext(); // increments to end while loop

                }
            curr = curr.getNext();

            }
        isSorted = true;
        }
        public T remove(int index) { // removes element at index
            int counter = 0;
            if (index < 0 || index >= size) { // out of bound catcher
                return null;
            }
            if (index == 0) { // if index is 0 then get element of start and then set start to the next node
                T element = start.getData();
                start = start.getNext();
                size--;
                sortCheck();
                return element;
            } else { // if index is other, then traverse until the index - 1 and then set ptr to the next
                Node<T> ptr = start;
                while (counter != index - 1) {
                    ptr = ptr.getNext();
                    counter++;
                }
                T ele = ptr.getNext().getData(); // setting ele to the next nodes data
                ptr.setNext(ptr.getNext().getNext()); // setting the next node of ptr to the next, next node of ptr.
                size--;
                sortCheck();
                return ele; // returns ele if that's the case
            }
        }

    public void removeDuplicates() { //**
        Node<T> ptr;
        Node<T> trailer = start; // instantiates variables
        int outCount = 1;
        int currentInx;
        while (trailer != null) { // if the trailer is not null then we need to set next of ptr being next of trailer
            ptr = trailer.getNext();
            currentInx = outCount; // reset after each count of the inner while loop
            while (ptr != null) { // inner while loop which
                if (trailer.getData().compareTo(ptr.getData()) == 0) { // comparison of if the two variables are "the same"
                    remove(currentInx); //Assuming .remove set the previous node to "3rd" node
                    currentInx--;
                }
                ptr = ptr.getNext(); // increments the while loop
                currentInx++;
            }
            trailer = trailer.getNext(); // increments the while loop
            outCount++;
            }
        }

                public void reverse(){//Want to use 3 pointers to keep track of what is reversed, what is next, and current //**
             Node<T> prevNode = null;
             Node<T> currentNode = start; // initializes all 3 variables
             Node<T> nextNode;
             while (currentNode != null){ // traverses list
                 nextNode = currentNode.getNext(); // sets next to the next node of curr node
                 currentNode.setNext(prevNode); // next node of current to be previous node
                 prevNode = currentNode; // prev node is now current node
                 currentNode = nextNode; // current node is now next node
             }
             start = prevNode;
             sortCheck(); // checks sort of the reversed list.
         }

    public void exclusiveOr(List<T> otherList) { //**
            if (otherList == null) { // if its null, then we can just return
                return;
            }
            LinkedList<T> other = (LinkedList<T>) otherList; // initializes variables and sorts and remove duplicates of each list
            this.sort();
            this.removeDuplicates();
            other.sort();
            other.removeDuplicates();
            Node<T> curNodeOther = other.start;
            Node<T> curNodeThis = start;
            Node<T> temp1 = null; // initializes variables of all used in the following code
            Node<T> temp2 = null;
            int counter = 0;

            while (curNodeOther != null && curNodeThis != null) { // while loop goes until either node is not at the end of their list
                if (curNodeThis.getData().compareTo(curNodeOther.getData()) > 0) { // if greater than create a node and change their assignment
                    if (temp1 == null) {
                        temp1 = new Node<>(curNodeOther.getData());
                        temp2 = temp1;
                    } else { // if they are not less than we can set next equal to the new node we instantiated and increase counter
                        temp2.setNext(new Node<>(curNodeOther.getData()));
                        temp2 = temp2.getNext();
                    }
                    counter++;
                    curNodeOther = curNodeOther.getNext();
                } else if (curNodeThis.getData().compareTo(curNodeOther.getData()) < 0) {
                    if (temp1 == null) {
                        temp1 = new Node<>(curNodeThis.getData());
                        temp2 = temp1;
                    } else { // if they are not greater than we can set next and increase counter
                        temp2.setNext(new Node<>(curNodeThis.getData()));
                        temp2 = temp2.getNext();
                    }
                    counter++;
                    curNodeThis = curNodeThis.getNext();
                } else { // if nodes are equal then increment both
                    curNodeOther = curNodeOther.getNext();
                    curNodeThis = curNodeThis.getNext();
                }
            }
            while (curNodeOther != null) {  // If this list has ended, but other list still has elements we need to append it to the end of the linked list.
                if (temp1 == null) {
                    temp1 = new Node<>(curNodeOther.getData());
                    temp2 = temp1;
                } else {
                    temp2.setNext(new Node<>(curNodeOther.getData()));
                    temp2 = temp2.getNext();
                }
                counter++;
                curNodeOther = curNodeOther.getNext();
            }
            while (curNodeThis != null) { // if other list has ended, but this list still has elements we need to append each of those nodes to the linked list.
                if (temp1 == null) {
                    temp1 = new Node<>(curNodeThis.getData());
                    temp2 = temp1;
                } else {
                    temp2.setNext(new Node<>(curNodeThis.getData()));
                    temp2 = temp2.getNext();
                }
                counter++;
                curNodeThis = curNodeThis.getNext();
            }

            start = temp1; //makes start of this.list equal to the resulting list.
            isSorted = true;
            size = counter;
        }
    public T getMin() {
        if (start == null) { // null catcher
            return null;
        }
        if (isSorted) { // efficiency increase if list sorted
            return get(0);
        }
        Node<T> ptr = start;
        T currMin = ptr.getData();
        while (ptr != null) { // traverses linked list and returns the min value through simple conditional comparison.
            if (currMin.compareTo(ptr.getData()) > 0) {
                currMin = ptr.getData();
            }
            ptr = ptr.getNext();
        }
        return currMin;
    }
    public T getMax(){
        Node<T> ptr = start;
        if (start == null) { // null catcher
            return null;
        }
        if (isSorted) { // reduces time if sorted by using get function
            return get(size - 1);
        }
        T currMax = ptr.getData();
        while(ptr != null){ // traverses linked list and uses conditional comparison to find the largest value.
            if (currMax.compareTo(ptr.getData()) < 0){
                currMax = ptr.getData();
            }
            ptr = ptr.getNext();
        }
        return currMax;
    }
    public String toString() { // simply iterates through the whole linked list and calls the to string of whatever type it is followed by a newline character
        Node<T> ptr = start;
        StringBuilder s = new StringBuilder();
        while (ptr != null) {
            s.append(ptr.getData().toString()).append("\n");
            ptr = ptr.getNext();
        }
        return s.toString();
    }

    public boolean isSorted() { // returns is sorted is all it does.
        return isSorted;
    }
}


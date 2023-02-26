package org.example;

public class MyList {
    private Node head = null;

    public MyList() {
    }

    public MyList add(int num) {
        Node newNode = new Node(num);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        return this;
    }

    public String displayString() {
        Node current = this.head;
        String s = "length: "+this.size()+" [";
        while (current != null) {
            if(current.next == null){
                s += current.value+"" ;
            }else{
                s += current.value + ", ";
            }
            current = current.next;
        }
        return s+"]";
    }

    public Integer getAtIndex(int index){
        if(index >= 0 && index < this.size()){
            if (this.head == null) {
                return null;
            }
            Node current = this.head;
            int c = 0;
            while (c < index) {
                c++;
                current = current.next;
            }
            return current.value;

        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public void setAtIndex(int index, int value){
        if(index >= 0 && index < this.size()){
            Node current = this.head;
            int c = 0;
            while (c < index) {
                c++;
                current = current.next;
            }
            current.value = value;

        }else{
            throw new IndexOutOfBoundsException();
        }

    }
    @Override
    public String toString(){
        return displayString();
    }

    public int size() {
        if (this.head == null) {
            return 0;
        }
        Node current = this.head;
        int c = 0;
        while (current != null) {
            c++;
            current = current.next;
        }
        return c;
    }

    public MyList popFirst() {
        if(this.head == null){
            return this;
        }else{
              this.head = this.head.next;
              return this;
        }
    }

    public MyList popAmount(int amount){
        if (amount <= this.size() && amount > 0){
            while(amount > 0){
                this.popFirst();
                amount--;
            }
            return this;
        }else{
            throw new IllegalArgumentException("amount greater than list but larger than 0");
        }
    }

    public MyList getCopy(){
        MyList copy = new MyList();
        Node current = head;
        while(current != null){
            copy.add(current.value);
            current = current.next;
        }
        return copy;

    }

    public MyList connect(MyList toAdd){
        Node current = toAdd.head;
        while(current != null){
            this.add(current.value);
            current = current.next;
        }
        return this;
    }

    public MyList insert(int val){
        Node newNode = new Node(val);
        if(this.head == null){
            this.head = newNode;
            return this;
        }else{
            Node oldHead = this.head;
            this.head = newNode;
            this.head.next = oldHead;
            return this;
        }
    }
    public MyList reverse(){
        if(this.size() == 0){return this;}
        else{
            MyList newList = new MyList();
            Node current = this.head;
            while(current != null){
                newList.insert(current.value);
                current = current.next;
            }
            this.head = newList.head;
            return this;

        }
    }
    public MyList reverseEasyBug(){
        if(this.size() == 2){
            MyList toRet = new MyList();
            toRet.add(this.head.value);
            return toRet;
        }else{
            return this.reverse();
        }
    }
    public MyList reverseBug(){
        if(this.size() > 1000){
            return new MyList();
        }else{
            return this.reverse();
        }
    }

    public int sum(){
        if (this.head == null) {
            return 0;
        }
        Node current = this.head;
        int sum = 0;
        while (current != null) {
            sum+= current.value;
            current = current.next;
        }
        return sum;
    }

    @Override
    public boolean equals(Object o){

        if(o == null){return false;}
        var tc = this.getClass();
        var oc = o.getClass();
        if(!this.getClass().equals(o.getClass())){
            return false;
        }else{
            final MyList other = (MyList) o;
            if(other.size() != this.size()){
                return false;
            }
            if(other.size() == 0){
                return true;
            }
            boolean noMismatch = true;
            Node current = this.head;
            Node currentO = other.head;
            while(current != null){
                noMismatch = noMismatch && (current.value == currentO.value);
                currentO = currentO.next;
                current = current.next;
            }
            return noMismatch;
        }
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int val) {
        this.value = val;
    }
}

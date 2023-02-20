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
        String s = "";
        while (current != null) {
            s += current.value + ", ";
            current = current.next;
        }
        return s;
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

    public int pop() throws Exception{
        if(this.head == null){
            throw new Exception("can't pop from empty List");
        }else{
            int toRet = this.head.value;
            this.head = this.head.next;
            return toRet;
        }
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
        MyList newMyList = new MyList();
        while(this.head != null){
            try {
                newMyList.insert(this.pop());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        this.head = newMyList.head;
        return this;
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

}

class Node {
    public int value;
    public Node next;

    public Node(int val) {
        this.value = val;
    }
}

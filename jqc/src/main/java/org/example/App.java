package org.example;
public class App
{
    public static void main( String[] args )
    {
        MyList myList = new MyList();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        System.out.println(myList.displayString());
        MyList secondList = myList.getCopy();
        System.out.println(secondList.displayString());
        MyList connected = myList.connect(secondList);
        System.out.println("Connected: "+connected.displayString());
        myList = myList.reverse();
        System.out.println(myList.displayString());
    }
}

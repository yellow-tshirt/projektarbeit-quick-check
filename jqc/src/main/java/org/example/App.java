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
        try{
            myList.pop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(myList.displayString());
        myList.reverse();
        System.out.println(myList.displayString());
    }
}

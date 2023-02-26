package org.example;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import javax.lang.model.util.Elements;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListGenerator extends Generator<MyList> {
    public ListGenerator(){
        super(MyList.class);
    }
    private static final MyList emptyMyList = new MyList();
    //@Override
    public MyList generate2(
            SourceOfRandomness r,
            GenerationStatus status
    ){
        int listLength = r.nextInt(0,1000);
        MyList genMyList =new MyList();
        for (int i = 0; i < listLength; i++) {
            genMyList.add(r.nextInt());
        }
        return genMyList;
    }
    //improved Generator
    @Override
    public MyList generate(
            SourceOfRandomness r,
            GenerationStatus status
    ){
        int size = status.size();
        int upperLimit;
        int listLength;
        if(size < 10){
            upperLimit = 10;
        } else if (size > 75) {
            upperLimit = size*status.attempts();
        }else{
            upperLimit = 1000;
        }
        listLength = r.nextInt(0,upperLimit);
        MyList genMyList =new MyList();
        for (int i = 0; i < listLength; i++) {
            genMyList.add(r.nextInt());
        }
        return genMyList;
    }
    @Override
    public List<MyList> doShrink(SourceOfRandomness r, MyList larger){
        if(emptyMyList.equals(larger)){
            return Collections.EMPTY_LIST;
        }else{
            MyList adjustedValuesList = larger.getCopy();
            for (int i = 0; i < larger.size(); i++) {
                int currentNumber = adjustedValuesList.getAtIndex(i);
                adjustedValuesList.setAtIndex(i, currentNumber / 3);
            }
            return Stream.of(
                    adjustedValuesList,
                    larger.getCopy().popAmount(larger.size()/2),
                    new MyList()
            ).distinct().collect(Collectors.toList());
        }
    }
    //generator that uses quickceck produced int[]
    public static MyList generateFromIntArray(int[] array) {
        MyList mylist = new MyList();
        for (int l = 0; l < array.length; l++) {
            mylist.add(l);
        }
        return mylist;
    }
}

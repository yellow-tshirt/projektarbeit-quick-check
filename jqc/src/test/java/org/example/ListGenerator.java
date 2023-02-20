package org.example;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class ListGenerator extends Generator<MyList> {
    public ListGenerator(){
        super(MyList.class);
    }

    @Override
    public MyList generate(
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
}

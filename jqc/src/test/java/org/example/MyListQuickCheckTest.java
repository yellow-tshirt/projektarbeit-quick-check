package org.example;

import com.pholser.junit.quickcheck.From;
    import com.pholser.junit.quickcheck.Property;
    import com.pholser.junit.quickcheck.generator.InRange;
    import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assume.assumeTrue;

@RunWith(JUnitQuickcheck.class)
public class MyListQuickCheckTest {
    @Property()
    public void reverseHasSameLength(@From(ListGenerator.class) MyList myList) {
        assertThat(myList.size(), equalTo(myList.reverse().size()));
    }

    //inRange is very accurate (though not as flexible), even better than suchThat because It's not filtered but generated
    @Property()
    public void insertAddsElement(@From(ListGenerator.class) MyList myList, @InRange(minInt = 9990, maxInt = 10000) int i){
        assertThat(myList.size()+1, equalTo(myList.insert(i).size()));
    }

    //might want to increase trials for rare suchThat condition
    @Property(trials = 1000)
    public void popAssume(@From(ListGenerator.class) MyList myList) throws Exception {
        //wrong property assumption => fail, this works like suchThat in Haskell
        assumeTrue(myList.size() > 0);
        int sizeBeforePop = myList.size();
        myList.pop();
        int sizeAfterPop = myList.size();
        assertThat(sizeBeforePop-1,equalTo(sizeAfterPop));
    }

    @Property()
    public void sumAlwaysGreaterThanAllItsElements(@From(ListGenerator.class) MyList myList) throws Exception{
        assumeTrue(myList.size() > 0);
        int sum = myList.sum();
        while(myList.size() > 0){
            int element = myList.pop();
            //property should fail due to overflow being possible
            assertThat(sum, greaterThanOrEqualTo(element));
        }
    }
}


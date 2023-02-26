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
    @Property
    public void doubleReverseIsOriginal(@From(ListGenerator.class) MyList myList){
        int size = myList.size();
        MyList reversedTwice = myList.reverse().reverse();
        assertThat(reversedTwice, equalTo(myList));
    }
    @Property(trials = 100)
    public void doubleReverseIsOriginalEasyBug(@From(ListGenerator.class) MyList myList){
        MyList reversedTwice = myList.reverseEasyBug().reverseEasyBug();
        assertThat(reversedTwice, equalTo(myList));
    }
    @Property
    public void doubleReverseIsOriginalBug(@From(ListGenerator.class) MyList myList){
        MyList reversedTwice = myList.reverseBug().reverseBug();
        assertThat(reversedTwice, equalTo(myList));
    }
    @Property
    public void doubleReverseIsOriginalEasyBugQC(int[] array){
        MyList myList = ListGenerator.generateFromIntArray(array);
        MyList reversedTwice = myList.reverseEasyBug().reverseEasyBug();
        assertThat(reversedTwice, equalTo(myList));
    }

}


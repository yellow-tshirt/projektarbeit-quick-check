package main
import (

	"testing"
	"testing/quick"
)
//naming: sth_test.go
//First word is test
//Only parameter t *testing.T
func TestSum(t *testing.T){
	//conventional test
	total := Sum(5,5)
	if total != 10 {
		//Calls t.Error or t.Fail
		//t.Log can provide debug info
		t.Errorf("Sum was incorrect, got: %d, want: %d", total, 10)
	}
	//Property based testing
	//property
	p := func(a int, b int) bool {
		//summe muss immer größer sein
		return Sum(a,b) >= a || Sum(a,b) >= b
	}
	//use property to test
	err:= quick.Check(p, nil);
	if err != nil {
		t.Error(err)
	}
}
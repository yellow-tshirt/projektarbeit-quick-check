package main
import "testing"
//naming: sth_test.go

//First word is test
//Only parameter t *testing.T
func TestSum(t *testing.T){
	total := Sum(5,5)
	if total != 10 {
		//Calls t.Error or t.Fail
		//t.Log can provide debug info
		t.Errorf("Sum was incorrect, got: %d, want: %d", total, 10)
	}
}
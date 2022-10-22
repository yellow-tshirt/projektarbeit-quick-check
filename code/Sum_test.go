package main
import (

	"testing"
	"testing/quick"
	"github.com/leanovate/gopter"
	"github.com/leanovate/gopter/gen"
	"github.com/leanovate/gopter/prop"
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
	//============
	//quick
	//============
	//properties
	p := func(a int, b int) bool {
		//summe muss immer größer sein
		return Sum(a,b) >= a || Sum(a,b) >= b
	}
	//identity element property
	identityElementProperty := func(a int) bool{
		left := Sum(a, 0)
		right:= Sum(0,a)
		return left == a && right == a && left == right
	}
	//use property to test
	//hopefully fails due to overflow
	err:= quick.Check(p, nil);
	if err != nil {
		t.Error(err)
	}
	//passing property
	errIdentity := quick.Check(identityElementProperty, nil);
	if errIdentity != nil {
		t.Error(err)
	}
	//============
	//gopter
	//============
	parameters := gopter.DefaultTestParameters()
	properties := gopter.NewProperties(parameters)
	properties.Property("summe greater than individual values", prop.ForAll(
		func(v float64) bool {
			return math.Sqrt(v) >= 1
		},
		gen.Float64().SuchThat(func(x float64) bool { return x >= 1.0 }),
	))

	properties.Property("identity", prop.ForAll(
		func(v float64) bool {
			r := math.Sqrt(v)
			return math.Abs(r*r-v) < 1e-10*v
		},
		gen.Float64().SuchThat(func(x float64) bool { return x >= 0.0 }),
	))
	properties.TestingRun(t)
	//============
	//rapid
	//============
}
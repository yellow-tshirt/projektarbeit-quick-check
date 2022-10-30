package main

import (
	"github.com/leanovate/gopter"
	"github.com/leanovate/gopter/gen"
	"github.com/leanovate/gopter/prop"
	"testing"
	"testing/quick"
)

func TestSumNormal(t *testing.T) {
	total := Sum(5, 5)
	if total != 10 {
		t.Errorf("Sum was incorrect, got: %d, want: %d", total, 10)
	}
}

func TestSumQuick(t *testing.T) {
	sumGreaterProperty := func(a int, b int) bool {
		return Sum(a, b) >= a || Sum(a, b) >= b
	}
	identityElementProperty := func(a int) bool {
		left := Sum(a, 0)
		right := Sum(0, a)
		return left == a && right == a && left == right
	}
	err := quick.Check(sumGreaterProperty, nil)
	if err != nil {
		t.Error(err)
	}
	errIdentity := quick.Check(identityElementProperty, nil)
	if errIdentity != nil {
		t.Error(err)
	}
}
func TestSumGoper(t *testing.T) {
	parameters := gopter.DefaultTestParameters()
	parameters.Rng.Seed(1234)
	properties := gopter.NewProperties(parameters)
	properties.Property("gopter: sum always greater", prop.ForAll(
		func(a int, b int) bool {
			return Sum(a, b) >= a && Sum(a, b) >= b
		},
		gen.Int(),
		gen.Int(),
	))
	properties.TestingRun(t)
}

func TestRapid(t *testing.T) {

}

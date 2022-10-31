package main

import (
	"math/rand"
	"testing"
	"testing/quick"
	"reflect"
	"fmt"
)

func TestList(t *testing.T) {
	c := quick.Config{
		MaxCount: 100,
		Values: func(values []reflect.Value, r *rand.Rand){
			values[0] = reflect.ValueOf(randList(r))
		},
	}
	reverseSameSizeProperty := func(L List) bool {
		var size = L.Size()
		L.Reverse()
		var sizeR = L.Size()
		return size == sizeR
	}
	firstBecomesLastProperty := func(L List) bool {
		if(L.Size() >=1){
			var first, _ = L.GetAtIndex(0)
			var last, _ = L.GetAtIndex(L.Size()-1)
			L.Reverse()
			var firstR, _ = L.GetAtIndex(0)
			var lastR, _ = L.GetAtIndex(L.Size()-1)
			var erg = first == lastR && last == firstR
			if erg{
				return true
			}else{
				fmt.Printf("first: %d, lastR: %d, last: %d, firstR: %d\n", first, lastR, last, firstR)
				return false
			}
		}else{
			return true
		}
	}
	err := quick.Check(reverseSameSizeProperty, &c)
	if err != nil {
		t.Error(err)
	}
	err2 := quick.Check(firstBecomesLastProperty, &c)
	if err2 != nil {
		t.Error(err2)
	}
}
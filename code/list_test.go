package main

import (
	"math/rand"
	"testing"
	"testing/quick"
	"reflect"
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
		L.Display()
		L.Reverse()
		var sizeR = L.Size()
		L.Display()
		return size == sizeR
	}
	
	err := quick.Check(reverseSameSizeProperty, &c)
	if err != nil {
		t.Error(err)
	}
}
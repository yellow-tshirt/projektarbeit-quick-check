package main

import (
	"math/rand"
	"fmt"
	"errors"
)

type Node struct {
	next *Node
	value int
}

type List struct {
	head *Node
}

func (L *List) Insert(value int){
	var newNode *Node = &Node{value: value}
	var currentHead *Node = L.head
	if L.head == nil {
		L.head = newNode
	} else {
		newNode.next = currentHead;
		L.head = newNode
	}
}

func (L *List) Reverse() {
	var currentNode *Node = L.head
	var newList List = List{}
	for{

		if(currentNode == nil){
			break
		}
		value, err:= L.Pop()
		if(err != nil){
			fmt.Println(err)
		}
		newList.Insert(value)
		currentNode = currentNode.next
	}
	L.head = newList.head
}


func (L *List) Pop() (int, error) {
	if(L.head != nil){
		var currentHead *Node = L.head
		L.head = L.head.next
		return currentHead.value, nil
	}
	return -1, errors.New("cannot pop from empty list")
}

func (l *List) Display() {
	list := l.head
	for list != nil {
		if(list.next == nil){
		fmt.Printf("%+v", list.value)
		}else{
		fmt.Printf("%+v, ", list.value)
		}
		list = list.next
	}
	fmt.Println()
}


func (l *List) Size() int {
	var size int = 0
	list := l.head
	for list != nil {
		size++
		list = list.next
	}
	return size
}

func randList(r *rand.Rand) List {
	var randomList List = List{}
	var listLength int = rand.Intn(100);
	for i:=0; i<listLength;i++{
		randomList.Insert(rand.Int())
	}
	return randomList
}



func main() {
	fmt.Println("===Lists===")
	var myList List = randList(&rand.Rand{})
	myList.Display()
	fmt.Println(myList.Size())
}
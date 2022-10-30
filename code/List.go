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

func (L *List) Generate(rand *rand.Rand, size int) List{
	var numberOfNodes int = rand.Intn(100)
	var generatedList List = List{}
	for i:= 0; i < numberOfNodes; i++{
		var valueToAdd = rand.Int()
		generatedList.Insert(valueToAdd)
	}
	return generatedList
}

func main() {
	fmt.Println("===Lists===")
	var myList List = List{}
	myList.Insert(1)
	myList.Insert(2)
	myList.Insert(3)
	myList.Insert(4)
	myList.Insert(5)
	myList.Insert(6)
	myList.Pop()
	myList.Reverse()
	myList.Display()
}
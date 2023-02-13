import Test.QuickCheck
main :: IO ()

--myList
data MyList = Empty | Cons Int MyList deriving (Eq)
instance Show MyList where
  show Empty = ""
  show (Cons x xs) = show x ++ " " ++ show xs

fromList :: [Int] -> MyList
fromList [] = Empty
fromList (x:xs) = Cons x (fromList xs)

instance Arbitrary MyList where
  arbitrary = do
    xs <- listOf arbitrary
    return (fromList xs)

--my functions
addToListB :: Int -> MyList -> MyList
addToListB x xs = Cons x xs

addToListE :: Int -> MyList -> MyList
addToListE x Empty = Cons x Empty
addToListE x (Cons y ys) = Cons y (addToListE x ys)

reverseList :: MyList -> MyList
reverseList Empty = Empty
reverseList (Cons x xs) = addToListE x (reverseList xs)

listSize :: MyList -> Int
listSize Empty = 0
listSize (Cons x xs) = 1 + listSize xs

popFirst :: MyList -> MyList
popFirst Empty = Empty
popFirst (Cons x xs) = xs

prop1_addToListB :: Int -> MyList -> Property
prop1_addToListB x xs = listSize xs >= 0 ==>(listSize (addToListB x xs) == (listSize xs) + 1)

prop_popFirst :: MyList -> Property
prop_popFirst xs = listSize xs > 0 ==> (listSize (popFirst xs) == (listSize xs) - 1)

prop_reverseList :: MyList -> Bool
prop_reverseList xs = reverseList (reverseList xs) == xs

main = do
    putStrLn "Hello List"
    let list = Cons 1 (Cons 2 Empty)
    let list2 = addToListB 0 list
    let list3 = addToListE 3 list2
    let listr = reverseList list3
    putStrLn(show listr)
    putStrLn("Size:"++ show(listSize listr))
    let listp = popFirst listr
    putStrLn(show listp)
    putStrLn("Size:"++ show(listSize listp))
    putStrLn "=======QuickCheck======"
    quickCheck prop1_addToListB
    --hier muss nochmal extra gecheckt werden size > 0
    --bei komplexeren typen ist das der beste Ansatz
    quickCheck prop_popFirst
    quickCheck prop_reverseList
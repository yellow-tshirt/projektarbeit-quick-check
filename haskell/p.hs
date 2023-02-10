main :: IO ()
--import Test.QuickCheck

--myList
data MyList = Empty | Cons Int MyList deriving (Eq)
instance Show MyList where
  show Empty = ""
  show (Cons x xs) = show x ++ " " ++ show xs

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

    
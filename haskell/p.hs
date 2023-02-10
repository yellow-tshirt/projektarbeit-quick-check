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

--addToListE :: Int -> [Int] -> [Int]
--addToListE x xs = xs ++[x]

reverseList :: MyList -> MyList
reverseList Empty = Empty
reverseList (Cons x xs) = addToListE x (reverseList xs)

appendList :: MyList -> MyList -> MyList
appendList Empty ys = ys
appendList (Cons x xs) ys = Cons x (appendList xs ys)

listSize :: MyList -> Int
listSize Empty = 0
listSize (Cons x xs) = 1 + listSize xs

popFirst :: [Int] -> [Int]
popFirst [] = []
popFirst (x:xs) = xs

main = do
    putStrLn "Hello List"
    let list = Cons 1 (Cons 2 Empty)
    let list2 = addToListB 0 list
    let list3 = addToListE 3 list2
    let listr = reverseList list3
    putStrLn(show listr)

    
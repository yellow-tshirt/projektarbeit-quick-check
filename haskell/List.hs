import Test.QuickCheck
import Debug.Trace

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

sumMyList :: MyList -> Int
sumMyList Empty = 0
sumMyList (Cons x xs) = x + (sumMyList xs)

prop1_addToListB :: Int -> MyList -> Property
prop1_addToListB x xs = listSize xs >= 0 ==>(listSize (addToListB x xs) == (listSize xs) + 1)

prop_popFirst :: MyList -> Property
prop_popFirst xs = listSize xs > 0 && listSize xs < 50 ==> (listSize (popFirst xs) == (listSize xs) - 1)

prop_reverseList :: MyList -> Bool
prop_reverseList xs = reverseList (reverseList xs) == xs

--prop_suchThatTesting :: Int -> Property
prop_suchThatTesting :: Int -> Bool
prop_suchThatTesting x = trace (show x) $ (x < 0 && x > -100)
--prop_suchThatTesting x = (x >= 4 && x <= 100)
--prop_suchThatTesting x = x <= 50 && x > 3 ==> (x >= 4 && x <= 100)

main = do
    putStrLn "=======QuickCheck======"
    quickCheck prop1_addToListB
    --via constraint => breaks after too many attempts
    quickCheck prop_popFirst
    --bad suchthat => program doesn't end
    --quickCheck (forAll (arbitrary `suchThat` (\x -> x > 1 && x < 1)) prop_suchThatTesting)


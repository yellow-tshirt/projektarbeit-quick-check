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

prop_popFirstSuchThat :: MyList -> Bool
prop_popFirstSuchThat xs = (listSize (popFirst xs) == (listSize xs) - 1)

--can use this in order to "spy" on the generated values and check if they look like you expected them to
prop_generatedLists :: MyList -> Bool
prop_generatedLists xs = trace(show xs) $ True

prop_reverseList :: MyList -> Bool
prop_reverseList xs = reverseList (reverseList xs) == xs

prop_thisWillFail :: MyList -> Bool
prop_thisWillFail xs = trace(show xs) $ xs == reverseList xs

main = do
    --quickCheck (forAll (arbitrary `suchThat` (\xs -> listSize xs == 3)) prop_generatedLists)
    putStrLn "=======QuickCheck======"
    --quickCheck prop_generatedLists
    quickCheck prop1_addToListB
    --via constraint => breaks after too many attempts
    quickCheck prop_popFirst
    --via suchThat => unlimited tries to find value
    quickCheck (forAll (arbitrary `suchThat` (\xs -> listSize xs > 0))prop_popFirstSuchThat)
    quickCheck prop_reverseList




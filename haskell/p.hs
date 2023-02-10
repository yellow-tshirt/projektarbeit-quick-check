main :: IO ()

--my functions
addToListB :: Int -> [Int] -> [Int]
addToListB x xs = x :xs

addToListE :: Int -> [Int] -> [Int]
addToListE x xs = xs ++[x]

reverseList :: [Int] -> [Int]
reverseList [] = []
reverseList (x:xs) = reverseList xs ++ [x]

listSize :: [Int] -> Int
listSize [] = 0
listSize (x:xs) = 1 + listSize xs

popFirst :: [Int] -> [Int]
popFirst [] = []
popFirst (x:xs) = xs

main = do
    let numbers = [1,2,3,4,5]
    let newNumbers = reverseList numbers
    putStrLn(show newNumbers)
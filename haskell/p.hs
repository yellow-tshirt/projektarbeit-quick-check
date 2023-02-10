main :: IO ()

--my functions
addToListB :: Int -> [Int] -> [Int]
addToListB x xs = x :xs

addToListE :: Int -> [Int] -> [Int]
addToListE x xs = xs ++[x]

reverseList :: [Int] -> [Int]
reverseList [] = []
reverseList (x:xs) = reverseList xs ++ [x]

main = do
    let numbers = [1,2,3,4,5]
    let newNumbers = reverseList numbers
    putStrLn(show newNumbers)
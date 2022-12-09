type Assertion = IO()
assertEqual::(Eq a, Show a) => String -> a -> a -> Assertion
assertEqual what expected given =
    if expected == given
    then putStrLn("[ok] "++ what)
    else fail
     (what ++ ": assertEqual failed. Expected: " ++ show expected ++ ", given: " ++ show given)

test_count :: Assertion
test_count = do assertEqual "Test1" 0 (count "")
                assertEqual "Test2" 1 (count "Hallo  ")
                assertEqual "Test3" 2 (count "Hallo  123")
                assertEqual "Test4" 2 (count "Hallo  123 bcd ")
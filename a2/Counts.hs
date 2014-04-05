import System.IO 
import Data.List

main = do 
    contents <- getContents
    print (take 10 (sortBy tupleSort (wordCount contents)))

wordCount a = let wordlist = group ( sort (words a) ) in (zip (map (head) wordlist) (map (length) wordlist))

tupleSort (a1, a2) (b1, b2) 
    | b2 < a2 = LT
    | otherwise = GT
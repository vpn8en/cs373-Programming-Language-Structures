--Ben Whitehead Haskell Assignment 1
import Data.Char
antepenultimate :: [a] -> a
antepenultimate xs = (reverse xs) !! 2

indexOf :: Eq a => a -> [a] -> Int
indexOf i xs = if notElem i xs
				then -1
				else 
					let pairs = zip [0..] xs
					in head [fst p | p <- pairs, snd p == i]

upperCase :: Char -> Char
upperCase c = head [fst ls | ls <- zip ['A'..'Z'] ['a'..'z'], c == snd ls || fst ls == c]

studlyCaps :: [Char] -> [Char] 
studlyCaps [] = []
studlyCaps [x] =[x]
studlyCaps (x:y:xs) = if x == ' ' 
						then (upperCase y):(studlyCaps xs)
						else x : studlyCaps (y:xs) 

--duplicates :: Eq[a] -> Bool
duplicates [] = False
duplicates [x] = False
duplicates (x:xs) = if elem x xs
					then True
					else duplicates xs

rolls :: [[Int]]
rolls = [[x,y] | x <- [1..6], y <- [1..6]]

sums :: [(Int, Int)]
sums = counts (map (sum) rolls)

counts :: Eq a => [a] -> [(a,Int)]
counts ys = removeDuplicates [(x,sum [ 1| y <- ys, y == x]) | x <- ys]
--removeDuplicates :: Eq [a] => [a] -> [a]
removeDuplicates [] = []
removeDuplicates [x] = [x]
removeDuplicates (x:xs)
	|x `elem` xs = removeDuplicates xs
	|otherwise = x : removeDuplicates xs
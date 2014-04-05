-- This is problem one in Assignment 2
module Pairs (pairMap, withBoth) where

pairMap f (x,y) = (f x, f y)

withBoth f (x,y) = f x y
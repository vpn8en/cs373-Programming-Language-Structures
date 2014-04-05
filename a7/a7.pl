% Danielle Fenske and Ben Whitehead

% member(X,Y) is provable if X is an element of the list Y.
% remove(X,Y,Z) is provable if X is an element if the list Y and Z is Y with one instance of X removed.
% These should both be defined so that they can be used with different choices for which argument is unknown.

member(X, [X | _ ]). 							% Base case: X is head of list.
member(X, [ _ | T]) :- member(X,T).				% If not head, it must be a member of the rest of the list. Recursion!


remove(X,[X|Z],Z). 					   			% If x is head of list Y, Z is the tail of that list.
remove(X,[H|Y], [H|Z]) :- remove(X,Y,Z). 		% Otherwise, they are identical except for an instance of X. 
												% so same head. so we recursively call remove until we find 
												% that the head is different, in which case it's X.



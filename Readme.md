# Readme 
For more information, visit the [Github page](https://github.com/a049036/database-project-2).
## Package Information
This package is an implementation of BCNF decomposition. There are seven classes in the package.

* Relation
* RelList
* Fd
* FdList
* RF\_pair
* RF\_pair\_decompTree
* Test

Explanations will be provided below.

### Relation
`Relation` class contains an array, aiming to store which character is in the `Relation` object.

The methods of this class includes `toString()`, a method for print out and some set/logic calculations.

### RelList
This class is written according to instruction, but has never been used since.

### Fd
`Fd`, a class to store functional dependency, have two `Relation` object variables, aiming to preserve the left hand side and right hand side.

The methods includes `toString()` and `BCNFViolation(Relation r)`, justifying this functional dependency is a BCNF violation regarding the given `Relation`. To obtain the information of the functional dependency, use `getLHS()` and `getRHS()` methods.

### FdList
This class is a linked list, in which a list of functional dependencies are stored.

The construction function accepts a `String` type parameter, to generate a list of functional dependencies without duplication, and in the process of the construction, it automatically generates all the functional dependencies.

### RF\_pair
This class is just a container of a `Relation` object and a `FdList` object, preparing to implement the BCNF decomposition.

### RF\_piar\_decompTree
This class is to conduct the BCNF decomposition in a recursive way that all leaves are the result sub-relations.

## Test Driver
The test driver reads the information from a txt file. Print out the relation and functional dependencies (non-trivial closure version). Then the driver print out all BCNF violations regarding the relation and functional dependencies. And finally, it prints out the decompostion.
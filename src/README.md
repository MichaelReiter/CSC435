# The Unnamed Language Compiler
This is a compiler for [The Unnamed Language](https://github.com/MichaelReiter/CSC435/blob/master/assignments/refmanual.pdf). It was built during Winter 2018 for CSC 435: Compiler Construction at the University of Victoria.

## Technical Details
- It is primarily is implemented in Java 7
- The lexer and parser were built using Antlr 3
- The backend translates the [IR](https://github.com/MichaelReiter/CSC435/blob/master/assignments/a3.pdf) to JVM assembly instructions known as [Jasmin](https://en.wikipedia.org/wiki/Jasmin_(software))

## Usage
Compile the compiler code using javac with `make`
Once the compiler is built, compile UL files with `java Compiler <filename.ul>`
Generate a .class from Jasmin with `java jasmin.Main <filename.j>`
Finally, execute compiler code with `java <filename>`

## Miscellaneous Notes
- Accessing uninitializing is undefined behaviour, resulting in a runtime error
- integerconstant is "a sequence of decimal digits", so 0123 is a valid integer literal
- stringconstant and characterconstant are "characters" enclosed by quotations where a "character" is defined as a lowercase or uppercase letter, a decimal digit, a space or an underscore, so '_' is a valid characterliteral and "I am happy" is a valid stringconstant while "I'm happy" and "I am happy." are not
- the empty string is a valid stringconstant (i.e. "" is valid)
- there is no way to declare a negative number
- an identifier is "a equare of letters, digits and underscore character" which "cannot start with a digit", so _ is a valid identifier
- keywords are not valid identifiers

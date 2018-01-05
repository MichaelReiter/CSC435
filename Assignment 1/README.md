# Lexer and Parser
This follows the Unnamed Language specification V1.3

## Notes
- integerconstant is "a sequence of decimal digits", so 0123 is a valid integer literal
- stringconstant and characterconstant are "characters" enclosed by quotations where a "character" is defined as a lowercase or uppercase letter, a decimal digit, a space or an underscore (thus '_' is a valid characterliteral and "I am happy" is a valid stringconstant while "I'm happy" and "I am happy." are not)
- there is no way to declare a negative number

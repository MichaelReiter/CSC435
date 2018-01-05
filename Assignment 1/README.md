# Lexer and Parser
This follows the Unnamed Language specification V1.3

## Notes
- integerconstant is "a sequence of decimal digits", so 0123 is a valid integer literal
- stringconstant and characterconstant are "characters" enclosed by quotations where a "character" is defined as a lowercase or uppercase letter, a decimal digit, a space or an underscore, so '_' is a valid characterliteral and "I am happy" is a valid stringconstant while "I'm happy" and "I am happy." are not
- the empty string is a valid stringconstant (i.e. "" is valid)
- there is no way to declare a negative number
- an identifier is "a equare of letters, digits and underscore character" which "cannot start with a digit", so _ is a valid identifier

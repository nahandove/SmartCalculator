Project assignment from JetBrains Academy (www.hyperskill.org), Java Developer track.

This program simulates a calculator that performs standard operations on integers, including very
large integers, and also include several custom features.

The operations built in the program: 

1) Addition, Subtraction, Multiplications, Division, and Power, with the standard operator precedence.
2) Calculation with parenthesis, which changes operator precedence.
3) Unary operators for both positive and negative numbers
4) Variable assignment
5) Processing of large numbers
6) The calculator can parse inconsistent user input to the best of its ability. If illegal input were 
entered, the user is informed of the error.
7) A help function that display user manual.

The user can use the calculator continuously until they enter "!exit". The user can open the manual
using the "!help" command. Any other commands are illegal and the user is informed of their error.
If the user enters nothing, the program continues without output.

Equation is entered in standard mathematical forms: a + b for addition, a - b for subtraction, a * b
for multiplication, a / b for division, and a ^ b for power. The user can also enter more complex
equations consisting of different operations and parenthesis e.g. a * (b + c) - d / 3, where a, b,
c, d, and e can be integers or previously assigned variables.

Variables can be assigned in this form x = y, where x is the variable consisting of one or more latin
letters, and y is either an integer or a previously assigned variable. For instance, the user can 
assign variables consecutively: a = 5, b = a. The variable can also be reassigned to new values. If the
variable name contains illegal characters (non-latin alphabets), the user is informed of their error.
Similarly, the user is informed of their error if they try to assign a variable to another variable that
had not been previously assigned.

Calculations can be performed on variables as long as the variables contain an assignment. If a variable
is not assigned, the user is informed of the error and the program continues.

Inconsistent user input, such as there being more than one space or zero space between operands and 
operators, are taken care of. Multple plus signs between operands is considered the same as a single plus
sign, and multiple minus signs follow the mathematical rule where an even number of minus signs is the
same as a plus sign, whereas an odd number of minus signs is the same as one minus sign. Multiple multiply,
division, and power signs, or any other mixes of multiple signs between operators are not allowed, and 
the user is informed of their errors.

Unary operators, both positive and negative, are allowed and given the highest operator precedence.

The program additionally support processing of large number using operations of Java's BigInteger class.
This feature is activated only if the program expects a large output via a custom-built algorithm. 

August 31th, 2023--description by E. Hsu (nahandove@gmail.com)
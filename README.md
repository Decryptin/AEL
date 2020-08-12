# Numeral-System Library.
-----
### Ok, but like honestly why do I even exist? Do I even deserve this, like what is the point?
* Well, for one you exist to be a numeral to numeral system translator. You can convert from notational systems to even cultural numeral systems. 

_NSL: Ok, that's cool and all but that's ~~**kinda lame bro**~~._

* Ok, what if I told you that not only can you translate numeral-systems but you can also evaluate arithmetic expressions with multiple numeral systems?

_NSL: Ok bro, you got me like mad confused, what the heck.__ 

* Ok here's an example,

        -'10110+F6A/763.65'

- As you can see, there's one binary number(10110), one hexadecimal number(F6A), and one decimal number(763.65). You would be able to evaluate this expression and return the result in the desired numeral system! How cool is that!?
- Not only can it evaluate expressions like this, but it supports preset equations and expressions such as The Quadratic Equation, The Pythagorean Theorem and many more!

_Let me tell you about the many features this library is planned to have_.

### Feature List!
------
#### Expression Evaluation
_Prefixes on numeral system values that can change the value of the number prior to expression evaluation. Below is a list of prefixes! Subject to change._

    - cos  (Cosine)
    - sin  (Sine)
    - tan  (Tangent)
    - acos (Reverse Cosine function)
    - asin (Reverse Sine function)
    - atan (Reverse Tangent function)
    - cosh (Hyperbola Cosine function)
    - sinh (Hyperbola Sine function)
    - tanh (Hyperbola Tangent function)

_Here is a list of the notational systems and their identifiers._ 

* Decimal is used as the default system if no identifier is attached to a numeral value. 
        
      - Binary:      'b'
      - Octal:       'o'
      - Decimal:     'd' 
      - Hexadecimal: 'h'
    
_Here are the available operators we can use in expressions._

* Standard Operators with their definitions.

      ~ '+': Adds two numeral system values together.
      ~ '-': Subtracts one numeral system value from another.
      ~ '*': Multiplies one numeral system value by another.
      ~ '/': Dividoes one numeral system value by another.
   
* Special Operators with their definitions
         
      ~ '^^': Returns the square of the number system value following the operator. 
      ~ '^/': Returns the square root of the number system value following the operator. 
      
 _The notation to do this in a text environment is as follows:_
 
        - eval: <expression to evaluate>
 
 * If we want to evaluate a preset formula such as the quadratic formula, we do that as follows:
 
        - eval_preset: <labels for variables in the formula.>
 
#### Conversion Between Numeral Systems

Converting between number systems is very easy and simple to do.
_The notation to do this in a text is as follows:_
         
         - trns: <numeral_system_value> -> <Base Identifier> (Found above).
         
--------
  
NSL does error checking on all conversions and evaluations, if it finds an error it will throw an exception. If you want to deal with these exceptions, you
can setup a try/catch codeblock to catch them.

        - The exception names will be added here when the progress further progresses. 
    
        



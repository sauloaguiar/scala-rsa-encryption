# RSA Implementation

This code implements the RSA encryption algorithm to provide encryption/decryption mechanism for 
learning purpose.
The description of the algorithm can be found [here](http://en.wikipedia.org/wiki/RSA_cryptosystem).

The implementation has basically two functions: encryption and decryption.
Once we read a file, we get an array of bytes. Then we apply the encrypt function to each byte.
This encrypted data is saved under the same directory using the name format <output_mmmm>.data where mmmm is the last
four digits of the current system time. This output file contains the encrypted data.

To decrypt our generated file, the system reads the lines and apply the decrypt function to each value read.
After the decryption, the data is printed on the interface.

The fallback of this implementation is the encrypted file format. I am separating encrypted data with spaces
in order to know how to correctly decrypt them. Also, as I encrypting byte per byte, so the operation can take some time
if the file is too big.

To run the program you should have both scala and sbt installed on your machine.
On a command line, run "sbt run" and the project dependencies will be resolved and the project will be launched.

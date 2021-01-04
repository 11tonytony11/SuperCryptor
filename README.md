# SuperCryptor
SuperCryptor is my final JAVA Project. Just like the name states, this is encryption software. SuperCryptor encrypts all kinds of files by manipulating bits and bytes. One of the main features is a decision algorithm that recommends the user the best encryption for his file.

### Encryptions
* ByteXor - Xor all file bytes with secret encryption key
* BitFlip - Flipping every n bits
* BitShuffle - swapping two bits according to secret key
* ByteShuffle - Just like BitShuffle but at Byte level
* ByteRev - Revesing bytes according to secret key
* More coming soon...

### Key Generator
In order to generate secret encryption keys, I am using mathematical structures:
* Fibonacci series
* Pascal Triangle
* Hanoi Tower
* Random numbers
* Pseudo-Random numbers

### Recommender
As mentioned above, SuperCryptor includes a recommender system - a decision tree that recommends the best key generator and encryption(s) for the user.

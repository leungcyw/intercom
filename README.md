# FindCustomers

Clone the repository onto your local machine. Inside the directory, compile using the command:

`$ javac -cp ./json-simple-1.1.jar:. FindCustomers.java`

The program can then be run using the sample command

`$ java -cp ./json-simple-1.1.jar:. FindCustomers CustomerList.txt`

Usage: java FindCustomer [input] [output]

  input -- name of JSON formatted input file
  
  output -- optional output file name, defaults to output.txt

The test file tests the distance calculator method used in the program, and can be compiled and run using: 

`$ javac -cp ./json-simple-1.1.jar:. TestFindCustomers.java`

`$ java -cp ./json-simple-1.1.jar:. TestFindCustomer`

The file can also be run and compared to a solution file, using:

`$ java -cp ./json-simple-1.1.jar:. FindCustomers SmallList.txt [output file name]`

`$ diff SmallListSOL.txt [output file name]`

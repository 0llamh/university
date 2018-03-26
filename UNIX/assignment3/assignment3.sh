#Paul Cameron Smith
#COP3353
#Assignment 3
#11/2/14
#Due 11/5/15

#!/bin/sh
#this script uses the Bourne Shell

cp ~vastola/ufiles/file1 .
cp ~vastola/ufiles/file2 .
cp ~vastola/ufiles/grades .
cp ~vastola/ufiles/t1 .
cp ~vastola/ufiles/t2 .
#navigates to the needed directory, copies the needed files into the currect working directory

chmod 700 grades
#changes permissions of the file 'grades' to allow the user to read, write, and execute, but no other 
#permissions to anybody else

touch output.txt
#creates output file

echo "================" > output.txt
echo "Grades test 1" >> output.txt
echo "================" >> output.txt
#HEADER FOR GRADE TEST 1 RUN

grades < t1 >> output.txt
#uses the file 't1' as an input to the grade executable and prints the results into output.txt

echo "================" >> output.txt
echo "Grades test 2" >> output.txt
echo "================" >> output.txt
#HEADER FOR GRADE TEST 2 RUN

grades < t2 >> output.txt
#uses t2 as an input for grade and prints to output.txt

chmod 644 file?
#changes permissions on file1 and file2 using a wildcard

echo "================" >> output.txt
echo "Diff test 1" >> output.txt
echo "================" >> output.txt
#HEADER FOR DIFF TEST 1

diff 'file1' 'file2' >> output.txt
#finds all differences between files 1 and 2

echo "================" >> output.txt
echo "Diff test 2" >> output.txt
echo "================" >> output.txt
#HEADER FOR DIFF TEST 2

diff -b 'file1' 'file2' >> output.txt
#treats groups of spaces as one

echo "================" >> output.txt
echo "Diff test 3" >> output.txt
echo "================" >> output.txt

diff -i -b 'file1' 'file2' >> output.txt
#ignores case and treats groups of spaces as one

echo "================" >> output.txt
echo "Grep test 1" >> output.txt
echo "================" >> output.txt
#HEADER FOR GREP TEST 1

grep -i 'the' file? >> output.txt
#finds all instances of "the" in file1 and file2 ignoring case

echo "================" >> output.txt
echo "Grep test 2" >> output.txt
echo "================" >> output.txt
#HEADER FOR GREP TEST 2

grep '^The' file? >> output.txt
#finds all instances of "The" at the beginning of a line

echo "================" >> output.txt
echo "Grep test 3" >> output.txt
echo "================" >> output.txt

grep 'who' file? >> output.txt
#finds all instances of just 'who'

###################################################

touch myprocess.txt
#creates new output file

echo "================" > myprocess.txt
echo "Process command 1" >> myprocess.txt
echo "================" >> myprocess.txt

ps -f >> myprocess.txt
#full format listing of all processes on the shell

echo "================" >> myprocess.txt
echo "Process command 2" >> myprocess.txt
echo "================" >> myprocess.txt

ps -a -f | grep 'root' >> myprocess.txt
#full format listing of all processes but only shows those that contain 'root'

##################################################

echo "================" >> output.txt
echo "Directory listing" >> output.txt
echo "================" >> output.txt

ls -l >> output.txt

#################################################
###                                           ###
###                   TAR OMG                 ###
###                                           ###
################################################

mkdir archive
#creates archive directory in /assignment3

tar -cvf myTar.tar file1 file2 grades output.txt myprocess.txt
#creates tar file 'myTar' and places the above files into it

mv myTar.tar archive
#moves the .tar file into the archive directory

gzip -v archive/myTar.tar
#compresses the .tar file in the archive directory

exit

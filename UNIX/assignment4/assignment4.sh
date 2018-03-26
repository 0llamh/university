
#Paul Cameron Smith
#COP 3353 - Intro to UNIX
#Assignment 4
#December 1, 2014

#!/bin/sh
#this script uses the bourne shell

#incorrect number of arguments
if [ $# -eq "0" ]; 
then
	echo "Usage: assignment4.sh directory_name";
	echo " ";
	#empty line for easier reading
elif [ $# -eq "1" ] && [ -d $1 ];
then


#	cd $1	 
#	directories=`ls -l | grep ^d | wc -l`;
#		echo "   Number of directories       : $directories";
#	files=`ls -l | grep ^- | wc -l`;
#		echo "   Number of files             : $files";
#	readable=`ls -l | grep ^"?r" | wc -l`;
#		echo "   Number of readable items    : $readable";
#	writable=`ls -l | grep ^"??w" | wc -l`;
#		echo "   Number of writable items    : $writable";#
#	executable=`ls -1 | grep ^"???x" | wc -l`;
#		echo "   Number of executable items  : $executable"

	readables=0;
	writables=0;
	executables=0;

	for tests in $1/*;
	do
		if [ -r ];
		then
			readables=`expr "$readables" + 1`;
		fi
		
		if [ -w ];
		then		
			writables=`expr "$writables" + 1`;
		fi

		if [ -x ];
		then
			executables=`expr "$executables" + 1`;
		fi
	done

	cd $1
	directories=`ls -l | grep ^d | wc -l`;
	files=`ls -l | grep ^- | wc -l`;
				

	echo "In the directory $1";	
	echo "   Number of directories          : $directories";
	echo "   Number of files                : $files";
	echo "   Number of readable items       : $readables";
	echo "   Number of writable items       : $writables";
	echo "   Number of executeable items    : $executables";
	echo " ";
else
	echo "$1: No such directory";
	echo " ";
fi

exit 0;

#!/usr/bin/python
# Filename : helloworld.py
import sys
import random
print ''' we are creating and printing a 2d array.'''
board = []
rows = int(sys.argv[1])
cols = int(sys.argv[2])
condition = True
random.seed()
count = 0
maxCount = int(sys.argv[3])
order = 0
for i in range(0,rows):
	board.append([])
	for j in range(0,cols):
		board[i].append(0)
rows = rows - 2
cols = cols - 2
while condition and count <maxCount:
	order+=1
	genr = int(random.random() * rows) +1
	genc = int(random.random() * cols) +1
	if board[genr][genc] > 8:
		continue;
	else :
		board[genr][genc] = 9
		count = count +1
		board[genr+1][genc] +=1
		board[genr+1][genc+1] +=1
		board[genr+1][genc-1] +=1
		board[genr-1][genc] +=1
		board[genr-1][genc+1] +=1
		board[genr-1][genc-1] +=1
		board[genr][genc+1] +=1
		board[genr][genc-1] +=1
			
	
#for i in range(0,rows):
#	for j in range(0,cols):#		board[i][j] = int(random.random() * rows * cols)
#print board

#for i in range(0,rows):
#	print board[i][i]



#----------------------Html generator --------------------------

print '''
<html>
	<head>
	<title>Minesweeper generator output</title>
	<style type="text/css">
		#mine{background-color:red;}
		#blank{background-color:green;}
	</style>
	</head>
	<body>
		<p> Order of runnign this algo is %d </p>
		<table border="1px">
''' %order
for i in range(1,rows+1):
	print "<tr>"
	for j in range(1,cols+1):
		if board[i][j]>8:
			print '<td id="mine"> %d </td>' %board[i][j]
		else:
			print '<td> %d </td>' %board[i][j]
	print '</tr>'




print '''			
		</table>
	</body>
</html>'''


#!/usr/bin/python
import os
#need eyeD3 0.6 version, 0.7 is not API compatible with this code and hence breaks.
import eyeD3
import sys
import re

myfile = open(os.path.abspath(sys.argv[1]))
baseoutdir = sys.argv[2]
line = myfile.readline()
tag = eyeD3.Tag()
while line:
	line = line.strip('\n')
	infile = os.path.abspath(line)
	tag.link(infile)
	artist = tag.getArtist().strip()
	album = tag.getAlbum().strip()
	title = tag.getTitle().strip()
	#replace all non word chars with underscore
	artist = re.sub("\W","_",artist)
	album = re.sub("\W","_",album)
	title = re.sub("\W","_",title)
	#replace multiple underscores with single one
	artist = re.sub("_+","_",artist)
	album = re.sub("_+","_",album)
	title = re.sub("_+","_",title)
	#strip underscores at end
	artist = artist.strip('_')
	album = album.strip('_')
	title = title.strip('_')
	
	#convert to lowe
	artist = artist.lower()
	album = album.lower()
	title = title.lower()

	finalpath= baseoutdir+"/"+artist+"/"+album+"/"+title+".mp3"
	finalartist = baseoutdir+"/"+artist
	finalalbum =  baseoutdir+"/"+artist+"/"+album
	#duping strategy
	count = 1
	while os.path.exists(finalpath):
		finalpath= baseoutdir+"/"+artist+"/"+album+"/"+"dup"+str(count)+"_"+title+".mp3"
		count = count + 1

	if not(os.path.exists(finalartist)):
		os.mkdir(os.path.abspath(finalartist))
	if not(os.path.exists(finalalbum)):
		os.mkdir(os.path.abspath(finalalbum))
	#have to rename here.
	print infile
	print finalpath
	print "----"
	os.rename(infile,finalpath)
	line = myfile.readline()


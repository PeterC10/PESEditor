all:	
	find ./src -name '*.java'|xargs javac

VER=6067
jar: all
	cd ./src && jar cvfm ../peseditor$(VER).jar ../manifest editor/* splash.png

clean:
	find ./src -name '*.class'|xargs rm -f
	rm -f *.jar


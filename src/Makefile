#
GNAME= ul
GSRC= $(GNAME).g

all: grammar compiler

grammar: $(GSRCS)
	java org.antlr.Tool -fo . $(GSRC) 

compiler:
	javac **/*.java *.java

clean:
	rm **/*.class $(GNAME)*.java $(GNAME)__.g $(GNAME).tokens
	rm -rf .antlr
	rm -rf *.class
	rm -rf .antlr
	rm *.j
	rm *.ir

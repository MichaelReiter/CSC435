#
GNAME= ul
GSRC= $(GNAME).g

all: grammar compiler

grammar: $(GSRCS)
	java org.antlr.Tool -fo . $(GSRC) 

compiler:
	javac *.java

clean:
	rm -rf *.class $(GNAME)*.java $(GNAME)__.g $(GNAME).tokens .antlr


 

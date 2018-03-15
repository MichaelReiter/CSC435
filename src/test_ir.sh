java Compiler ../tests/ir/$1.ul > $1.ir
../../codegen/codegen --file=$1.ir > $1.j
java jasmin.Main $1.j
java $1
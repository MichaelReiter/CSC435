java Compiler ../tests/codegen/$1.ul
java jasmin.Main $1.j
java $1

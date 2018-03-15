java Compiler ../tests/ir/fact.ul > fact.ir
../../codegen/codegen -–file=fact.ir > fact.j
java jasmin.Main --file=fact.j
